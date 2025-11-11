package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpcafeziedsnoussi.entities.Commande;
import tn.esprit.tpcafeziedsnoussi.enums.StatusCommande;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {


    List<Commande> findByStatusCommande(StatusCommande statusCommande);
    @Query("SELECT c FROM Commande c WHERE c.statusCommande = :statusCommande")
    List<Commande> findByStatusCommandeJPQL(@Param("statusCommande") StatusCommande statusCommande);
    @Query(value = "SELECT * FROM commande WHERE status_commande = :statusCommande", nativeQuery = true)
    List<Commande> findByStatusCommandeNative(@Param("statusCommande") String statusCommande);

    List<Commande> findByDateCommande(LocalDate dateCommande);
    @Query("SELECT c FROM Commande c WHERE c.dateCommande = :dateCommande")
    List<Commande> findByDateCommandeJPQL(@Param("dateCommande") LocalDate dateCommande);
    @Query(value = "SELECT * FROM commande WHERE date_commande = :dateCommande", nativeQuery = true)
    List<Commande> findByDateCommandeNative(@Param("dateCommande") LocalDate dateCommande);

    long countByStatusCommande(StatusCommande statusCommande);
    @Query("SELECT COUNT(c) FROM Commande c WHERE c.statusCommande = :statusCommande")
    long countByStatusCommandeJPQL(@Param("statusCommande") StatusCommande statusCommande);
    @Query(value = "SELECT COUNT(*) FROM commande WHERE status_commande = :statusCommande", nativeQuery = true)
    long countByStatusCommandeNative(@Param("statusCommande") String statusCommande);

    @Modifying
    @Transactional
    void deleteByDateCommandeBefore(LocalDate date);
    @Modifying
    @Transactional
    @Query("DELETE FROM Commande c WHERE c.dateCommande < :date")
    void deleteByDateCommandeBeforeJPQL(@Param("date") LocalDate date);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM commande WHERE date_commande < :date", nativeQuery = true)
    void deleteByDateCommandeBeforeNative(@Param("date") LocalDate date);

    List<Commande> findByStatusCommandeAndDateCommandeBetween(StatusCommande statusCommande, LocalDate startDate, LocalDate endDate);
    @Query("SELECT c FROM Commande c WHERE c.statusCommande = :status AND c.dateCommande BETWEEN :startDate AND :endDate")
    List<Commande> findByStatusAndDateRangeJPQL(@Param("status") StatusCommande status, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query(value = "SELECT * FROM commande WHERE status_commande = :status AND date_commande BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Commande> findByStatusAndDateRangeNative(@Param("status") String status, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Commande> findByTotalCommandeGreaterThanAndStatusCommandeNot(float total, StatusCommande statusCommande);
    @Query("SELECT c FROM Commande c WHERE c.totalCommande > :total AND c.statusCommande <> :status")
    List<Commande> findByTotalGreaterThanAndStatusNotJPQL(@Param("total") float total, @Param("status") StatusCommande status);
    @Query(value = "SELECT * FROM commande WHERE total_commande > :total AND status_commande <> :status", nativeQuery = true)
    List<Commande> findByTotalGreaterThanAndStatusNotNative(@Param("total") float total, @Param("status") String status);

    List<Commande> findByStatusCommandeInOrderByDateCommandeAsc(List<StatusCommande> statuses);
    @Query("SELECT c FROM Commande c WHERE c.statusCommande IN :statuses ORDER BY c.dateCommande ASC")
    List<Commande> findByStatusInOrderByDateJPQL(@Param("statuses") List<StatusCommande> statuses);
    @Query(value = "SELECT * FROM commande WHERE status_commande IN :statuses ORDER BY date_commande ASC", nativeQuery = true)
    List<Commande> findByStatusInOrderByDateNative(@Param("statuses") List<String> statuses);

    List<Commande> findByDateCommandeBeforeAndTotalCommandeBetween(LocalDate date, float minTotal, float maxTotal);
    @Query("SELECT c FROM Commande c WHERE c.dateCommande < :date AND c.totalCommande BETWEEN :minTotal AND :maxTotal")
    List<Commande> findByDateBeforeAndTotalBetweenJPQL(@Param("date") LocalDate date, @Param("minTotal") float minTotal, @Param("maxTotal") float maxTotal);
    @Query(value = "SELECT * FROM commande WHERE date_commande < :date AND total_commande BETWEEN :minTotal AND :maxTotal", nativeQuery = true)
    List<Commande> findByDateBeforeAndTotalBetweenNative(@Param("date") LocalDate date, @Param("minTotal") float minTotal, @Param("maxTotal") float maxTotal);

    @Query("SELECT c FROM Commande c WHERE CAST(c.statusCommande AS string) LIKE CONCAT('%', :suffix)")
    List<Commande> findByStatusEndingWithJPQL(@Param("suffix") String suffix);
    @Query(value = "SELECT * FROM commande WHERE status_commande LIKE CONCAT('%', :suffix)", nativeQuery = true)
    List<Commande> findByStatusEndingWithNative(@Param("suffix") String suffix);

    List<Commande> findByStatusCommandeIsNull();
    @Query("SELECT c FROM Commande c WHERE c.statusCommande IS NULL")
    List<Commande> findByStatusCommandeIsNullJPQL();
    @Query(value = "SELECT * FROM commande WHERE status_commande IS NULL", nativeQuery = true)
    List<Commande> findByStatusCommandeIsNullNative();

    List<Commande> findByTotalCommandeIsNotNull();
    @Query("SELECT c FROM Commande c WHERE c.totalCommande IS NOT NULL")
    List<Commande> findByTotalCommandeIsNotNullJPQL();
    @Query(value = "SELECT * FROM commande WHERE total_commande IS NOT NULL", nativeQuery = true)
    List<Commande> findByTotalCommandeIsNotNullNative();

    @Query("SELECT DISTINCT c FROM Commande c LEFT JOIN FETCH c.detailCommandes LEFT JOIN FETCH c.client")
    List<Commande> findAllWithDetailsAndClientJPQL();
    @Query(value = "SELECT DISTINCT c.* FROM commande c " +
           "LEFT JOIN detail_commande dc ON c.id_commande = dc.id_commande " +
           "LEFT JOIN client cl ON c.id_client = cl.id_client", nativeQuery = true)
    List<Commande> findAllWithDetailsAndClientNative();

    List<Commande> findTop3ByOrderByDateCommandeDesc();
    @Query("SELECT c FROM Commande c ORDER BY c.dateCommande DESC")
    List<Commande> findTop3RecentCommandesJPQL();
    @Query(value = "SELECT * FROM commande ORDER BY date_commande DESC LIMIT 3", nativeQuery = true)
    List<Commande> findTop3RecentCommandesNative();


    @Query("SELECT c FROM Commande c WHERE c.statusCommande <> :status")
    List<Commande> findByStatusCommandeNot(@Param("status") StatusCommande status);

    @Query("SELECT c FROM Commande c WHERE c.dateCommande <> :date")
    List<Commande> findByDateCommandeNot(@Param("date") LocalDate date);

    List<Commande> findByDateCommandeAfter(LocalDate date);

    List<Commande> findByDateCommandeBefore(LocalDate date);

    List<Commande> findByDateCommandeBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT c FROM Commande c WHERE c.dateCommande NOT BETWEEN :startDate AND :endDate")
    List<Commande> findByDateCommandeNotBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Commande> findByTotalCommande(float totalCommande);

    @Query("SELECT c FROM Commande c WHERE c.totalCommande <> :total")
    List<Commande> findByTotalCommandeNot(@Param("total") float total);

    List<Commande> findByTotalCommandeGreaterThan(float total);

    List<Commande> findByTotalCommandeLessThan(float total);

    List<Commande> findByTotalCommandeBetween(float minTotal, float maxTotal);

    @Query("SELECT c FROM Commande c WHERE c.totalCommande NOT BETWEEN :minTotal AND :maxTotal")
    List<Commande> findByTotalCommandeNotBetween(@Param("minTotal") float minTotal, @Param("maxTotal") float maxTotal);

    @Query("SELECT c FROM Commande c WHERE c.client.idClient = :clientId")
    List<Commande> findByClientId(@Param("clientId") Long clientId);

    @Query("SELECT c FROM Commande c WHERE c.client.nom = :nom")
    List<Commande> findByClientNom(@Param("nom") String nom);

    @Query("SELECT c FROM Commande c WHERE LOWER(c.client.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Commande> findByClientNomContaining(@Param("keyword") String keyword);

    @Query("SELECT c FROM Commande c WHERE LOWER(c.client.nom) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Commande> findByClientNomStartingWith(@Param("prefix") String prefix);

    @Query("SELECT c FROM Commande c WHERE LOWER(c.client.nom) NOT LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Commande> findByClientNomNotContaining(@Param("keyword") String keyword);

    @Query("SELECT c FROM Commande c WHERE c.statusCommande = :status AND c.dateCommande BETWEEN :startDate AND :endDate")
    List<Commande> findByStatusAndDateRange(@Param("status") StatusCommande status, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(c.totalCommande) FROM Commande c WHERE c.dateCommande BETWEEN :startDate AND :endDate")
    Float calculateTotalRevenue(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}

