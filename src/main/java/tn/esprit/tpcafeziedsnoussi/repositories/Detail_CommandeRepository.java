package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpcafeziedsnoussi.entities.Detail_Commande;

import java.util.List;
import java.util.Optional;

public interface Detail_CommandeRepository extends JpaRepository<Detail_Commande, Long> {

    // Find order details by quantity
    List<Detail_Commande> findByQuantiteArticle(int quantiteArticle);

    // Find order details with quantity NOT equal
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.quantiteArticle <> :quantite")
    List<Detail_Commande> findByQuantiteArticleNot(@Param("quantite") int quantite);

    // Find order details with quantity greater than
    List<Detail_Commande> findByQuantiteArticleGreaterThan(int quantite);

    // Find order details with quantity less than
    List<Detail_Commande> findByQuantiteArticleLessThan(int quantite);

    // Find order details with quantity between range
    List<Detail_Commande> findByQuantiteArticleBetween(int minQuantite, int maxQuantite);

    // Find order details with quantity NOT between range
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.quantiteArticle NOT BETWEEN :minQuantite AND :maxQuantite")
    List<Detail_Commande> findByQuantiteArticleNotBetween(@Param("minQuantite") int minQuantite, @Param("maxQuantite") int maxQuantite);

    // Find order details by subtotal
    List<Detail_Commande> findBySousTotalDetailArticle(float sousTotalDetailArticle);

    // Find order details with subtotal NOT equal
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticle <> :sousTotal")
    List<Detail_Commande> findBySousTotalDetailArticleNot(@Param("sousTotal") float sousTotal);

    // Find order details with subtotal greater than
    List<Detail_Commande> findBySousTotalDetailArticleGreaterThan(float sousTotal);

    // Find order details with subtotal less than
    List<Detail_Commande> findBySousTotalDetailArticleLessThan(float sousTotal);

    // Find order details with subtotal between range
    List<Detail_Commande> findBySousTotalDetailArticleBetween(float minSousTotal, float maxSousTotal);

    // Find order details with subtotal NOT between range
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticle NOT BETWEEN :minSousTotal AND :maxSousTotal")
    List<Detail_Commande> findBySousTotalDetailArticleNotBetween(@Param("minSousTotal") float minSousTotal, @Param("maxSousTotal") float maxSousTotal);

    // Find order details with subtotal after promotion greater than
    List<Detail_Commande> findBySousTotalDetailArticleApresPromoGreaterThan(float sousTotal);

    // Find order details with subtotal after promotion less than
    List<Detail_Commande> findBySousTotalDetailArticleApresPromoLessThan(float sousTotal);

    // Find order details with subtotal after promotion between range
    List<Detail_Commande> findBySousTotalDetailArticleApresPromoBetween(float minSousTotal, float maxSousTotal);

    // Custom query to find order details by order ID
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.commande.idCommande = :commandeId")
    List<Detail_Commande> findByCommandeId(@Param("commandeId") Long commandeId);

    // Custom query to find order details by article ID
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.article.idArticle = :articleId")
    List<Detail_Commande> findByArticleId(@Param("articleId") Long articleId);

    // Custom query to find order details by article name
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.article.nomArticle = :nomArticle")
    List<Detail_Commande> findByArticleNom(@Param("nomArticle") String nomArticle);

    // Custom query to find order details by article name containing (ignore case)
    @Query("SELECT dc FROM Detail_Commande dc WHERE LOWER(dc.article.nomArticle) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Detail_Commande> findByArticleNomContaining(@Param("keyword") String keyword);

    // Custom query to find order details by article name starting with
    @Query("SELECT dc FROM Detail_Commande dc WHERE LOWER(dc.article.nomArticle) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Detail_Commande> findByArticleNomStartingWith(@Param("prefix") String prefix);

    // Custom query to find order details by article name ending with
    @Query("SELECT dc FROM Detail_Commande dc WHERE LOWER(dc.article.nomArticle) LIKE LOWER(CONCAT('%', :suffix))")
    List<Detail_Commande> findByArticleNomEndingWith(@Param("suffix") String suffix);

    // Custom query to find order details by article name NOT containing
    @Query("SELECT dc FROM Detail_Commande dc WHERE LOWER(dc.article.nomArticle) NOT LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Detail_Commande> findByArticleNomNotContaining(@Param("keyword") String keyword);

    // Custom query to find order details with discount applied
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticleApresPromo < dc.sousTotalDetailArticle")
    List<Detail_Commande> findDetailsWithDiscount();

    // Custom query to find order details without discount
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticleApresPromo = dc.sousTotalDetailArticle")
    List<Detail_Commande> findDetailsWithoutDiscount();

    // Custom query to calculate total quantity sold for an article
    @Query("SELECT SUM(dc.quantiteArticle) FROM Detail_Commande dc WHERE dc.article.idArticle = :articleId")
    Integer calculateTotalQuantitySoldByArticle(@Param("articleId") Long articleId);

    // Custom query to find most sold articles
    @Query("SELECT dc.article.nomArticle, SUM(dc.quantiteArticle) as totalQuantity FROM Detail_Commande dc GROUP BY dc.article.nomArticle ORDER BY totalQuantity DESC")
    List<Object[]> findMostSoldArticles();

}

