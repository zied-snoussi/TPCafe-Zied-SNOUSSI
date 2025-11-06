package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpcafeziedsnoussi.entities.CarteFidelite;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarteFideliteRepository extends JpaRepository<CarteFidelite, Long> {

    // Find loyalty cards by accumulated points
    List<CarteFidelite> findByPointsAccumules(int pointsAccumules);

    // Find loyalty cards with points greater than
    List<CarteFidelite> findByPointsAccumulesGreaterThan(int points);

    // Find loyalty cards with points less than
    List<CarteFidelite> findByPointsAccumulesLessThan(int points);

    // Find loyalty cards with points NOT equal
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules <> :points")
    List<CarteFidelite> findByPointsAccumulesNot(@Param("points") int points);

    // Find loyalty cards with points between range
    List<CarteFidelite> findByPointsAccumulesBetween(int minPoints, int maxPoints);

    // Find loyalty cards with points NOT between range
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules NOT BETWEEN :minPoints AND :maxPoints")
    List<CarteFidelite> findByPointsAccumulesNotBetween(@Param("minPoints") int minPoints, @Param("maxPoints") int maxPoints);

    // Find loyalty cards by creation date
    List<CarteFidelite> findByDateCreation(LocalDate dateCreation);

    // Find loyalty cards NOT created on a specific date
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.dateCreation <> :date")
    List<CarteFidelite> findByDateCreationNot(@Param("date") LocalDate date);

    // Find loyalty cards created after a specific date
    List<CarteFidelite> findByDateCreationAfter(LocalDate date);

    // Find loyalty cards created before a specific date
    List<CarteFidelite> findByDateCreationBefore(LocalDate date);

    // Find loyalty cards created between dates
    List<CarteFidelite> findByDateCreationBetween(LocalDate startDate, LocalDate endDate);

    // Find loyalty cards NOT created between dates
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.dateCreation NOT BETWEEN :startDate AND :endDate")
    List<CarteFidelite> findByDateCreationNotBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Custom query to find loyalty cards by client name
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.client.nom = :nom")
    List<CarteFidelite> findByClientNom(@Param("nom") String nom);

    // Custom query to find loyalty cards by client name containing (ignore case)
    @Query("SELECT cf FROM CarteFidelite cf WHERE LOWER(cf.client.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<CarteFidelite> findByClientNomContaining(@Param("keyword") String keyword);

    // Custom query to find loyalty cards by client name starting with
    @Query("SELECT cf FROM CarteFidelite cf WHERE LOWER(cf.client.nom) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<CarteFidelite> findByClientNomStartingWith(@Param("prefix") String prefix);

    // Custom query to find top loyalty cards by points
    @Query("SELECT cf FROM CarteFidelite cf ORDER BY cf.pointsAccumules DESC")
    List<CarteFidelite> findTopLoyaltyCards();

    // ==================== CUSTOM QUERIES ====================

    // 1. Trouver les cartes avec un nombre exact de points
    List<CarteFidelite> findByPointsAccumulesEquals(int points);

    // 2. Trouver les cartes créées à une date spécifique
    List<CarteFidelite> findByDateCreationEquals(LocalDate date);

    // 3. Compter les cartes avec plus de X points
    long countByPointsAccumulesGreaterThan(int points);

    // 4. Supprimer les cartes créées avant une date
    @Modifying
    @Transactional
    @Query("DELETE FROM CarteFidelite cf WHERE cf.dateCreation < :date")
    void deleteByDateCreationBefore(@Param("date") LocalDate date);

    // 5. Trouver les cartes avec des points dans une plage, créées après une date
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules BETWEEN :minPoints AND :maxPoints AND cf.dateCreation > :date")
    List<CarteFidelite> findByPointsInRangeAndCreatedAfter(@Param("minPoints") int minPoints, 
                                                            @Param("maxPoints") int maxPoints, 
                                                            @Param("date") LocalDate date);

    // 6. Trouver les cartes avec au moins X points, triées par date de création
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules >= :minPoints ORDER BY cf.dateCreation ASC")
    List<CarteFidelite> findByMinPointsOrderByDateCreation(@Param("minPoints") int minPoints);

    // 7. Trouver les cartes créées entre deux dates
    List<CarteFidelite> findByDateCreationBetweenOrderByDateCreationAsc(LocalDate startDate, LocalDate endDate);

    // 8. Trouver les cartes avec peu de points OU créées avant une date
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules < :maxPoints OR cf.dateCreation < :date")
    List<CarteFidelite> findByLowPointsOrCreatedBefore(@Param("maxPoints") int maxPoints, 
                                                        @Param("date") LocalDate date);

    // 9. Trouver la carte avec le plus de points
    Optional<CarteFidelite> findTopByOrderByPointsAccumulesDesc();

    // 10. Trouver les cartes sans date de création
    List<CarteFidelite> findByDateCreationIsNull();

    // 11. Trouver les cartes avec des points accumulés renseignés
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules IS NOT NULL")
    List<CarteFidelite> findByPointsAccumulesIsNotNull();

    // 12. Trouver les cartes avec leur client propriétaire (Par nom et prénom)
    @Query("SELECT cf FROM CarteFidelite cf JOIN FETCH cf.client c WHERE c.nom = :nom AND c.prenom = :prenom")
    List<CarteFidelite> findByClientNomAndPrenom(@Param("nom") String nom, 
                                                  @Param("prenom") String prenom);

    // 13. Trouver top 5 des cartes avec le plus de points
    List<CarteFidelite> findTop5ByOrderByPointsAccumulesDesc();

}

