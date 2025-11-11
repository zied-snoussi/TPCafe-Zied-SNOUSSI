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

    List<CarteFidelite> findByPointsAccumules(int pointsAccumules);
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules = :pointsAccumules")
    List<CarteFidelite> findByPointsAccumulesJPQL(@Param("pointsAccumules") int pointsAccumules);
    @Query(value = "SELECT * FROM carte_fidelite WHERE points_accumules = :pointsAccumules", nativeQuery = true)
    List<CarteFidelite> findByPointsAccumulesNative(@Param("pointsAccumules") int pointsAccumules);

    List<CarteFidelite> findByDateCreation(LocalDate dateCreation);
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.dateCreation = :dateCreation")
    List<CarteFidelite> findByDateCreationJPQL(@Param("dateCreation") LocalDate dateCreation);
    @Query(value = "SELECT * FROM carte_fidelite WHERE date_creation = :dateCreation", nativeQuery = true)
    List<CarteFidelite> findByDateCreationNative(@Param("dateCreation") LocalDate dateCreation);

    long countByPointsAccumulesGreaterThan(int points);
    @Query("SELECT COUNT(cf) FROM CarteFidelite cf WHERE cf.pointsAccumules > :points")
    long countByPointsAccumulesGreaterThanJPQL(@Param("points") int points);
    @Query(value = "SELECT COUNT(*) FROM carte_fidelite WHERE points_accumules > :points", nativeQuery = true)
    long countByPointsAccumulesGreaterThanNative(@Param("points") int points);

    @Modifying
    @Transactional
    void deleteByDateCreationBefore(LocalDate date);
    @Modifying
    @Transactional
    @Query("DELETE FROM CarteFidelite cf WHERE cf.dateCreation < :date")
    void deleteByDateCreationBeforeJPQL(@Param("date") LocalDate date);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM carte_fidelite WHERE date_creation < :date", nativeQuery = true)
    void deleteByDateCreationBeforeNative(@Param("date") LocalDate date);

    List<CarteFidelite> findByPointsAccumulesBetweenAndDateCreationAfter(int minPoints, int maxPoints, LocalDate date);
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules BETWEEN :minPoints AND :maxPoints AND cf.dateCreation > :date")
    List<CarteFidelite> findByPointsInRangeAndCreatedAfterJPQL(@Param("minPoints") int minPoints, @Param("maxPoints") int maxPoints, @Param("date") LocalDate date);
    @Query(value = "SELECT * FROM carte_fidelite WHERE points_accumules BETWEEN :minPoints AND :maxPoints AND date_creation > :date", nativeQuery = true)
    List<CarteFidelite> findByPointsInRangeAndCreatedAfterNative(@Param("minPoints") int minPoints, @Param("maxPoints") int maxPoints, @Param("date") LocalDate date);

    List<CarteFidelite> findByPointsAccumulesGreaterThanEqualOrderByDateCreationAsc(int minPoints);
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules >= :minPoints ORDER BY cf.dateCreation ASC")
    List<CarteFidelite> findByMinPointsOrderByDateCreationJPQL(@Param("minPoints") int minPoints);
    @Query(value = "SELECT * FROM carte_fidelite WHERE points_accumules >= :minPoints ORDER BY date_creation ASC", nativeQuery = true)
    List<CarteFidelite> findByMinPointsOrderByDateCreationNative(@Param("minPoints") int minPoints);

    List<CarteFidelite> findByDateCreationBetweenOrderByDateCreationAsc(LocalDate startDate, LocalDate endDate);
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.dateCreation BETWEEN :startDate AND :endDate ORDER BY cf.dateCreation ASC")
    List<CarteFidelite> findByDateCreationBetweenOrderByDateCreationAscJPQL(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query(value = "SELECT * FROM carte_fidelite WHERE date_creation BETWEEN :startDate AND :endDate ORDER BY date_creation ASC", nativeQuery = true)
    List<CarteFidelite> findByDateCreationBetweenOrderByDateCreationAscNative(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<CarteFidelite> findByPointsAccumulesLessThanOrDateCreationBefore(int maxPoints, LocalDate date);
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules < :maxPoints OR cf.dateCreation < :date")
    List<CarteFidelite> findByLowPointsOrCreatedBeforeJPQL(@Param("maxPoints") int maxPoints, @Param("date") LocalDate date);
    @Query(value = "SELECT * FROM carte_fidelite WHERE points_accumules < :maxPoints OR date_creation < :date", nativeQuery = true)
    List<CarteFidelite> findByLowPointsOrCreatedBeforeNative(@Param("maxPoints") int maxPoints, @Param("date") LocalDate date);

    Optional<CarteFidelite> findTopByOrderByPointsAccumulesDesc();
    @Query("SELECT cf FROM CarteFidelite cf ORDER BY cf.pointsAccumules DESC")
    Optional<CarteFidelite> findTopByOrderByPointsAccumulesDescJPQL();
    @Query(value = "SELECT * FROM carte_fidelite ORDER BY points_accumules DESC LIMIT 1", nativeQuery = true)
    Optional<CarteFidelite> findTopByOrderByPointsAccumulesDescNative();

    List<CarteFidelite> findByDateCreationIsNull();
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.dateCreation IS NULL")
    List<CarteFidelite> findByDateCreationIsNullJPQL();
    @Query(value = "SELECT * FROM carte_fidelite WHERE date_creation IS NULL", nativeQuery = true)
    List<CarteFidelite> findByDateCreationIsNullNative();

    List<CarteFidelite> findByPointsAccumulesIsNotNull();
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules IS NOT NULL")
    List<CarteFidelite> findByPointsAccumulesIsNotNullJPQL();
    @Query(value = "SELECT * FROM carte_fidelite WHERE points_accumules IS NOT NULL", nativeQuery = true)
    List<CarteFidelite> findByPointsAccumulesIsNotNullNative();

    @Query("SELECT cf FROM CarteFidelite cf JOIN FETCH cf.client c WHERE c.nom = :nom AND c.prenom = :prenom")
    List<CarteFidelite> findByClientNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);
    @Query("SELECT cf FROM CarteFidelite cf JOIN FETCH cf.client c WHERE c.nom = :nom AND c.prenom = :prenom")
    List<CarteFidelite> findByClientNomAndPrenomJPQL(@Param("nom") String nom, @Param("prenom") String prenom);
    @Query(value = "SELECT cf.* FROM carte_fidelite cf INNER JOIN client c ON cf.id_client = c.id_client WHERE c.nom = :nom AND c.prenom = :prenom", nativeQuery = true)
    List<CarteFidelite> findByClientNomAndPrenomNative(@Param("nom") String nom, @Param("prenom") String prenom);

    List<CarteFidelite> findTop5ByOrderByPointsAccumulesDesc();
    @Query("SELECT cf FROM CarteFidelite cf ORDER BY cf.pointsAccumules DESC")
    List<CarteFidelite> findTop5ByOrderByPointsAccumulesDescJPQL();
    @Query(value = "SELECT * FROM carte_fidelite ORDER BY points_accumules DESC LIMIT 5", nativeQuery = true)
    List<CarteFidelite> findTop5ByOrderByPointsAccumulesDescNative();

    List<CarteFidelite> findByPointsAccumulesGreaterThan(int points);
    List<CarteFidelite> findByPointsAccumulesLessThan(int points);
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules <> :points")
    List<CarteFidelite> findByPointsAccumulesNot(@Param("points") int points);
    List<CarteFidelite> findByPointsAccumulesBetween(int minPoints, int maxPoints);
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAccumules NOT BETWEEN :minPoints AND :maxPoints")
    List<CarteFidelite> findByPointsAccumulesNotBetween(@Param("minPoints") int minPoints, @Param("maxPoints") int maxPoints);
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.dateCreation <> :date")
    List<CarteFidelite> findByDateCreationNot(@Param("date") LocalDate date);
    List<CarteFidelite> findByDateCreationAfter(LocalDate date);
    List<CarteFidelite> findByDateCreationBefore(LocalDate date);
    List<CarteFidelite> findByDateCreationBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.dateCreation NOT BETWEEN :startDate AND :endDate")
    List<CarteFidelite> findByDateCreationNotBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.client.nom = :nom")
    List<CarteFidelite> findByClientNom(@Param("nom") String nom);
    @Query("SELECT cf FROM CarteFidelite cf WHERE LOWER(cf.client.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<CarteFidelite> findByClientNomContaining(@Param("keyword") String keyword);
    @Query("SELECT cf FROM CarteFidelite cf WHERE LOWER(cf.client.nom) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<CarteFidelite> findByClientNomStartingWith(@Param("prefix") String prefix);
    @Query("SELECT cf FROM CarteFidelite cf ORDER BY cf.pointsAccumules DESC")
    List<CarteFidelite> findTopLoyaltyCards();

}

