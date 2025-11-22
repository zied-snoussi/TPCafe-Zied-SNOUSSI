package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.enums.TypeArticle;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {


    List<Client> findByNom(String nom);
    @Query("SELECT c FROM Client c WHERE c.nom = :nom")
    List<Client> findByNomJPQL(@Param("nom") String nom);
    @Query(value = "SELECT * FROM client WHERE nom = :nom", nativeQuery = true)
    List<Client> findByNomNative(@Param("nom") String nom);

    List<Client> findByPrenom(String prenom);
    @Query("SELECT c FROM Client c WHERE c.prenom = :prenom")
    List<Client> findByPrenomJPQL(@Param("prenom") String prenom);
    @Query(value = "SELECT * FROM client WHERE prenom = :prenom", nativeQuery = true)
    List<Client> findByPrenomNative(@Param("prenom") String prenom);

    Optional<Client> findByNomAndPrenom(String nom, String prenom);
    @Query("SELECT c FROM Client c WHERE c.nom = :nom AND c.prenom = :prenom")
    Optional<Client> findByNomAndPrenomJPQL(@Param("nom") String nom, @Param("prenom") String prenom);
    @Query(value = "SELECT * FROM client WHERE nom = :nom AND prenom = :prenom", nativeQuery = true)
    Optional<Client> findByNomAndPrenomNative(@Param("nom") String nom, @Param("prenom") String prenom);

    boolean existsByNom(String nom);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE c.nom = :nom")
    boolean existsByNomJPQL(@Param("nom") String nom);
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM client WHERE nom = :nom", nativeQuery = true)
    int existsByNomNative(@Param("nom") String nom);

    long countByDateNaissanceAfter(LocalDate date);
    @Query("SELECT COUNT(c) FROM Client c WHERE c.dateNaissance > :date")
    long countByDateNaissanceAfterJPQL(@Param("date") LocalDate date);
    @Query(value = "SELECT COUNT(*) FROM client WHERE date_naissance > :date", nativeQuery = true)
    long countByDateNaissanceAfterNative(@Param("date") LocalDate date);

    List<Client> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.prenom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Client> findByNomOrPrenomContainingJPQL(@Param("keyword") String keyword);
    @Query(value = "SELECT * FROM client WHERE LOWER(nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(prenom) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Client> findByNomOrPrenomContainingNative(@Param("keyword") String keyword);

    List<Client> findByNomContainingIgnoreCaseAndPrenomContainingIgnoreCase(String nom, String prenom);
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :nomKeyword, '%')) AND LOWER(c.prenom) LIKE LOWER(CONCAT('%', :prenomKeyword, '%'))")
    List<Client> findByNomAndPrenomContainingJPQL(@Param("nomKeyword") String nomKeyword, @Param("prenomKeyword") String prenomKeyword);
    @Query(value = "SELECT * FROM client WHERE LOWER(nom) LIKE LOWER(CONCAT('%', :nomKeyword, '%')) AND LOWER(prenom) LIKE LOWER(CONCAT('%', :prenomKeyword, '%'))", nativeQuery = true)
    List<Client> findByNomAndPrenomContainingNative(@Param("nomKeyword") String nomKeyword, @Param("prenomKeyword") String prenomKeyword);

    List<Client> findByDateNaissanceBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT c FROM Client c WHERE c.dateNaissance BETWEEN :startDate AND :endDate")
    List<Client> findByDateNaissanceBetweenJPQL(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query(value = "SELECT * FROM client WHERE date_naissance BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Client> findByDateNaissanceBetweenNative(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Client> findByNomStartingWithIgnoreCaseAndDateNaissanceBefore(String prefix, LocalDate date);
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT(:prefix, '%')) AND c.dateNaissance < :date")
    List<Client> findByNomStartingWithAndDateNaissanceBeforeJPQL(@Param("prefix") String prefix, @Param("date") LocalDate date);
    @Query(value = "SELECT * FROM client WHERE LOWER(nom) LIKE LOWER(CONCAT(:prefix, '%')) AND date_naissance < :date", nativeQuery = true)
    List<Client> findByNomStartingWithAndDateNaissanceBeforeNative(@Param("prefix") String prefix, @Param("date") LocalDate date);

    @Query("SELECT c FROM Client c WHERE c.adresse.ville = :ville")
    List<Client> findByVilleJPQL(@Param("ville") String ville);
    @Query(value = "SELECT c.* FROM client c INNER JOIN adresse a ON c.id_adresse = a.id_adresse WHERE a.ville = :ville", nativeQuery = true)
    List<Client> findByVilleNative(@Param("ville") String ville);

    List<Client> findByNomContainingIgnoreCaseOrderByPrenomAsc(String nom);
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :nom, '%')) ORDER BY c.prenom ASC")
    List<Client> findByNomContainingOrderByPrenomAscJPQL(@Param("nom") String nom);
    @Query(value = "SELECT * FROM client WHERE LOWER(nom) LIKE LOWER(CONCAT('%', :nom, '%')) ORDER BY prenom ASC", nativeQuery = true)
    List<Client> findByNomContainingOrderByPrenomAscNative(@Param("nom") String nom);

    List<Client> findByNomContainingIgnoreCaseOrderByPrenomDesc(String nom);
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :nom, '%')) ORDER BY c.prenom DESC")
    List<Client> findByNomContainingOrderByPrenomDescJPQL(@Param("nom") String nom);
    @Query(value = "SELECT * FROM client WHERE LOWER(nom) LIKE LOWER(CONCAT('%', :nom, '%')) ORDER BY prenom DESC", nativeQuery = true)
    List<Client> findByNomContainingOrderByPrenomDescNative(@Param("nom") String nom);

    List<Client> findByNomStartingWithIgnoreCase(String prefix);
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Client> findByNomStartingWithJPQL(@Param("prefix") String prefix);
    @Query(value = "SELECT * FROM client WHERE LOWER(nom) LIKE LOWER(CONCAT(:prefix, '%'))", nativeQuery = true)
    List<Client> findByNomStartingWithNative(@Param("prefix") String prefix);

    List<Client> findByPrenomEndingWithIgnoreCase(String suffix);
    @Query("SELECT c FROM Client c WHERE LOWER(c.prenom) LIKE LOWER(CONCAT('%', :suffix))")
    List<Client> findByPrenomEndingWithJPQL(@Param("suffix") String suffix);
    @Query(value = "SELECT * FROM client WHERE LOWER(prenom) LIKE LOWER(CONCAT('%', :suffix))", nativeQuery = true)
    List<Client> findByPrenomEndingWithNative(@Param("suffix") String suffix);

    List<Client> findByDateNaissanceIsNull();
    @Query("SELECT c FROM Client c WHERE c.dateNaissance IS NULL")
    List<Client> findByDateNaissanceIsNullJPQL();
    @Query(value = "SELECT * FROM client WHERE date_naissance IS NULL", nativeQuery = true)
    List<Client> findByDateNaissanceIsNullNative();

    List<Client> findByAdresseIsNotNull();
    @Query("SELECT c FROM Client c WHERE c.adresse IS NOT NULL")
    List<Client> findByAdresseIsNotNullJPQL();
    @Query(value = "SELECT * FROM client WHERE id_adresse IS NOT NULL", nativeQuery = true)
    List<Client> findByAdresseIsNotNullNative();

    @Query("SELECT c FROM Client c WHERE c.adresse.ville IN :villes")
    List<Client> findByVilleInJPQL(@Param("villes") List<String> villes);
    @Query(value = "SELECT c.* FROM client c INNER JOIN adresse a ON c.id_adresse = a.id_adresse WHERE a.ville IN :villes", nativeQuery = true)
    List<Client> findByVilleInNative(@Param("villes") List<String> villes);

    @Query("SELECT c FROM Client c WHERE c.carteFidelite.pointsAccumules > :points")
    List<Client> findByPointsAccumulesGreaterThanJPQL(@Param("points") int points);
    @Query(value = "SELECT c.* FROM client c INNER JOIN carte_fidelite cf ON c.id_carte_fidelite = cf.id_carte_fidelite WHERE cf.points_accumules > :points", nativeQuery = true)
    List<Client> findByPointsAccumulesGreaterThanNative(@Param("points") int points);

    @Query("SELECT c FROM Client c WHERE c.carteFidelite.pointsAccumules >= :points")
    List<Client> findByPointsAccumulesGreaterThanEqualJPQL(@Param("points") int points);
    @Query(value = "SELECT c.* FROM client c INNER JOIN carte_fidelite cf ON c.id_carte_fidelite = cf.id_carte_fidelite WHERE cf.points_accumules >= :points", nativeQuery = true)
    List<Client> findByPointsAccumulesGreaterThanEqualNative(@Param("points") int points);

    @Query("SELECT c FROM Client c WHERE c.carteFidelite.pointsAccumules BETWEEN :minPoints AND :maxPoints")
    List<Client> findByPointsAccumulesBetweenJPQL(@Param("minPoints") int minPoints, @Param("maxPoints") int maxPoints);
    @Query(value = "SELECT c.* FROM client c INNER JOIN carte_fidelite cf ON c.id_carte_fidelite = cf.id_carte_fidelite WHERE cf.points_accumules BETWEEN :minPoints AND :maxPoints", nativeQuery = true)
    List<Client> findByPointsAccumulesBetweenNative(@Param("minPoints") int minPoints, @Param("maxPoints") int maxPoints);

    @Query("SELECT DISTINCT c FROM Client c JOIN c.commandes cmd JOIN cmd.detailCommandes dc JOIN dc.article a WHERE a.nomArticle = :nomArticle")
    List<Client> findByArticleCommandeJPQL(@Param("nomArticle") String nomArticle);
    @Query(value = "SELECT DISTINCT c.* FROM client c " +
           "INNER JOIN commande cmd ON c.id_client = cmd.id_client " +
           "INNER JOIN detail_commande dc ON cmd.id_commande = dc.id_commande " +
           "INNER JOIN article a ON dc.id_article = a.id_article " +
           "WHERE a.nom_article = :nomArticle", nativeQuery = true)
    List<Client> findByArticleCommandeNative(@Param("nomArticle") String nomArticle);

    @Query("SELECT DISTINCT c FROM Client c JOIN c.commandes cmd JOIN cmd.detailCommandes dc JOIN dc.article a " +
           "WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :nomKeyword, '%')) AND a.typeArticle = :typeArticle")
    List<Client> findByNomContainingAndArticleTypeJPQL(@Param("nomKeyword") String nomKeyword, @Param("typeArticle") TypeArticle typeArticle);
    @Query(value = "SELECT DISTINCT c.* FROM client c " +
           "INNER JOIN commande cmd ON c.id_client = cmd.id_client " +
           "INNER JOIN detail_commande dc ON cmd.id_commande = dc.id_commande " +
           "INNER JOIN article a ON dc.id_article = a.id_article " +
           "WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :nomKeyword, '%')) AND a.type_article = :typeArticle", nativeQuery = true)
    List<Client> findByNomContainingAndArticleTypeNative(@Param("nomKeyword") String nomKeyword, @Param("typeArticle") String typeArticle);


    List<Client> findByNomContainingIgnoreCase(String nom);

    List<Client> findByNomEndingWithIgnoreCase(String suffix);

    @Query("SELECT c FROM Client c WHERE c.nom NOT LIKE %:keyword%")
    List<Client> findByNomNotContaining(@Param("keyword") String keyword);

    @Query("SELECT c FROM Client c WHERE c.nom NOT LIKE :prefix%")
    List<Client> findByNomNotStartingWith(@Param("prefix") String prefix);

    List<Client> findByDateNaissance(LocalDate dateNaissance);

    List<Client> findByDateNaissanceAfter(LocalDate date);

    List<Client> findByDateNaissanceBefore(LocalDate date);

    @Query("SELECT c FROM Client c WHERE c.dateNaissance <> :date")
    List<Client> findByDateNaissanceNot(@Param("date") LocalDate date);

    @Query("SELECT c FROM Client c WHERE c.adresse.ville = :ville")
    List<Client> findByVille(@Param("ville") String ville);

    @Query("SELECT c FROM Client c WHERE c.carteFidelite.pointsAccumules > :points")
    List<Client> findByPointsAccumulesGreaterThan(@Param("points") int points);

    @Query("SELECT c FROM Client c WHERE c.carteFidelite.pointsAccumules <> :points")
    List<Client> findByPointsAccumulesNot(@Param("points") int points);

}
