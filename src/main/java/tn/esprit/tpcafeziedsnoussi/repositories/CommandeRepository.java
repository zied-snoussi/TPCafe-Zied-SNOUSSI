package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpcafeziedsnoussi.entities.Commande;
import tn.esprit.tpcafeziedsnoussi.enums.StatusCommande;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    // Find orders by status
    List<Commande> findByStatusCommande(StatusCommande statusCommande);

    // Find orders NOT of a specific status
    @Query("SELECT c FROM Commande c WHERE c.statusCommande <> :status")
    List<Commande> findByStatusCommandeNot(@Param("status") StatusCommande status);

    // Find orders by date
    List<Commande> findByDateCommande(LocalDate dateCommande);

    // Find orders NOT on a specific date
    @Query("SELECT c FROM Commande c WHERE c.dateCommande <> :date")
    List<Commande> findByDateCommandeNot(@Param("date") LocalDate date);

    // Find orders after a specific date
    List<Commande> findByDateCommandeAfter(LocalDate date);

    // Find orders before a specific date
    List<Commande> findByDateCommandeBefore(LocalDate date);

    // Find orders between dates
    List<Commande> findByDateCommandeBetween(LocalDate startDate, LocalDate endDate);

    // Find orders NOT between dates
    @Query("SELECT c FROM Commande c WHERE c.dateCommande NOT BETWEEN :startDate AND :endDate")
    List<Commande> findByDateCommandeNotBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Find orders by total
    List<Commande> findByTotalCommande(float totalCommande);

    // Find orders with total NOT equal
    @Query("SELECT c FROM Commande c WHERE c.totalCommande <> :total")
    List<Commande> findByTotalCommandeNot(@Param("total") float total);

    // Find orders with total greater than
    List<Commande> findByTotalCommandeGreaterThan(float total);

    // Find orders with total less than
    List<Commande> findByTotalCommandeLessThan(float total);

    // Find orders with total between range
    List<Commande> findByTotalCommandeBetween(float minTotal, float maxTotal);

    // Find orders with total NOT between range
    @Query("SELECT c FROM Commande c WHERE c.totalCommande NOT BETWEEN :minTotal AND :maxTotal")
    List<Commande> findByTotalCommandeNotBetween(@Param("minTotal") float minTotal, @Param("maxTotal") float maxTotal);

    // Custom query to find orders by client ID
    @Query("SELECT c FROM Commande c WHERE c.client.idClient = :clientId")
    List<Commande> findByClientId(@Param("clientId") Long clientId);

    // Custom query to find orders by client name
    @Query("SELECT c FROM Commande c WHERE c.client.nom = :nom")
    List<Commande> findByClientNom(@Param("nom") String nom);

    // Custom query to find orders by client name containing (ignore case)
    @Query("SELECT c FROM Commande c WHERE LOWER(c.client.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Commande> findByClientNomContaining(@Param("keyword") String keyword);

    // Custom query to find orders by client name starting with
    @Query("SELECT c FROM Commande c WHERE LOWER(c.client.nom) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Commande> findByClientNomStartingWith(@Param("prefix") String prefix);

    // Custom query to find orders by client name NOT containing
    @Query("SELECT c FROM Commande c WHERE LOWER(c.client.nom) NOT LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Commande> findByClientNomNotContaining(@Param("keyword") String keyword);

    // Custom query to find orders by status and date range
    @Query("SELECT c FROM Commande c WHERE c.statusCommande = :status AND c.dateCommande BETWEEN :startDate AND :endDate")
    List<Commande> findByStatusAndDateRange(@Param("status") StatusCommande status, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Custom query to calculate total revenue by date range
    @Query("SELECT SUM(c.totalCommande) FROM Commande c WHERE c.dateCommande BETWEEN :startDate AND :endDate")
    Float calculateTotalRevenue(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}

