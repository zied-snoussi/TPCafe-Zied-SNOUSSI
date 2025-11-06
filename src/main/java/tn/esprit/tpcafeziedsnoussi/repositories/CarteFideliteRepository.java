package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

}

