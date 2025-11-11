package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpcafeziedsnoussi.entities.Detail_Commande;

import java.util.List;
import java.util.Optional;

public interface Detail_CommandeRepository extends JpaRepository<Detail_Commande, Long> {


    List<Detail_Commande> findByQuantiteArticle(int quantiteArticle);
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.quantiteArticle = :quantiteArticle")
    List<Detail_Commande> findByQuantiteArticleJPQL(@Param("quantiteArticle") int quantiteArticle);
    @Query(value = "SELECT * FROM detail_commande WHERE quantite_article = :quantiteArticle", nativeQuery = true)
    List<Detail_Commande> findByQuantiteArticleNative(@Param("quantiteArticle") int quantiteArticle);

    List<Detail_Commande> findBySousTotalDetailArticle(float sousTotalDetailArticle);
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticle = :sousTotalDetailArticle")
    List<Detail_Commande> findBySousTotalDetailArticleJPQL(@Param("sousTotalDetailArticle") float sousTotalDetailArticle);
    @Query(value = "SELECT * FROM detail_commande WHERE sous_total_detail_article = :sousTotalDetailArticle", nativeQuery = true)
    List<Detail_Commande> findBySousTotalDetailArticleNative(@Param("sousTotalDetailArticle") float sousTotalDetailArticle);

    long countByQuantiteArticleGreaterThan(int quantite);
    @Query("SELECT COUNT(dc) FROM Detail_Commande dc WHERE dc.quantiteArticle > :quantite")
    long countByQuantiteArticleGreaterThanJPQL(@Param("quantite") int quantite);
    @Query(value = "SELECT COUNT(*) FROM detail_commande WHERE quantite_article > :quantite", nativeQuery = true)
    long countByQuantiteArticleGreaterThanNative(@Param("quantite") int quantite);

    boolean existsBySousTotalDetailArticleGreaterThan(float sousTotal);
    @Query("SELECT CASE WHEN COUNT(dc) > 0 THEN true ELSE false END FROM Detail_Commande dc WHERE dc.sousTotalDetailArticle > :sousTotal")
    boolean existsBySousTotalDetailArticleGreaterThanJPQL(@Param("sousTotal") float sousTotal);
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM detail_commande WHERE sous_total_detail_article > :sousTotal", nativeQuery = true)
    int existsBySousTotalDetailArticleGreaterThanNative(@Param("sousTotal") float sousTotal);

    List<Detail_Commande> findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThanEqual(int minQuantite, int maxQuantite, float minSousTotal);
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.quantiteArticle BETWEEN :minQuantite AND :maxQuantite AND dc.sousTotalDetailArticle >= :minSousTotal")
    List<Detail_Commande> findByQuantiteBetweenAndSousTotalMinJPQL(@Param("minQuantite") int minQuantite, @Param("maxQuantite") int maxQuantite, @Param("minSousTotal") float minSousTotal);
    @Query(value = "SELECT * FROM detail_commande WHERE quantite_article BETWEEN :minQuantite AND :maxQuantite AND sous_total_detail_article >= :minSousTotal", nativeQuery = true)
    List<Detail_Commande> findByQuantiteBetweenAndSousTotalMinNative(@Param("minQuantite") int minQuantite, @Param("maxQuantite") int maxQuantite, @Param("minSousTotal") float minSousTotal);

    List<Detail_Commande> findBySousTotalDetailArticleBetweenOrderByQuantiteArticleAsc(float minSousTotal, float maxSousTotal);
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticle BETWEEN :minSousTotal AND :maxSousTotal ORDER BY dc.quantiteArticle ASC")
    List<Detail_Commande> findBySousTotalBetweenOrderByQuantiteJPQL(@Param("minSousTotal") float minSousTotal, @Param("maxSousTotal") float maxSousTotal);
    @Query(value = "SELECT * FROM detail_commande WHERE sous_total_detail_article BETWEEN :minSousTotal AND :maxSousTotal ORDER BY quantite_article ASC", nativeQuery = true)
    List<Detail_Commande> findBySousTotalBetweenOrderByQuantiteNative(@Param("minSousTotal") float minSousTotal, @Param("maxSousTotal") float maxSousTotal);

    List<Detail_Commande> findBySousTotalDetailArticleApresPromoBetween(float minSousTotal, float maxSousTotal);
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticleApresPromo BETWEEN :minSousTotal AND :maxSousTotal")
    List<Detail_Commande> findBySousTotalApresPromoBetweenJPQL(@Param("minSousTotal") float minSousTotal, @Param("maxSousTotal") float maxSousTotal);
    @Query(value = "SELECT * FROM detail_commande WHERE sous_total_detail_article_apres_promo BETWEEN :minSousTotal AND :maxSousTotal", nativeQuery = true)
    List<Detail_Commande> findBySousTotalApresPromoBetweenNative(@Param("minSousTotal") float minSousTotal, @Param("maxSousTotal") float maxSousTotal);

    List<Detail_Commande> findByQuantiteArticleGreaterThanOrSousTotalDetailArticleGreaterThan(int quantite, float sousTotal);
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.quantiteArticle > :quantite OR dc.sousTotalDetailArticle > :sousTotal")
    List<Detail_Commande> findByQuantiteOrSousTotalGreaterThanJPQL(@Param("quantite") int quantite, @Param("sousTotal") float sousTotal);
    @Query(value = "SELECT * FROM detail_commande WHERE quantite_article > :quantite OR sous_total_detail_article > :sousTotal", nativeQuery = true)
    List<Detail_Commande> findByQuantiteOrSousTotalGreaterThanNative(@Param("quantite") int quantite, @Param("sousTotal") float sousTotal);

    List<Detail_Commande> findTop5ByOrderBySousTotalDetailArticleDesc();
    @Query("SELECT dc FROM Detail_Commande dc ORDER BY dc.sousTotalDetailArticle DESC")
    List<Detail_Commande> findTop5MostExpensiveJPQL();
    @Query(value = "SELECT * FROM detail_commande ORDER BY sous_total_detail_article DESC LIMIT 5", nativeQuery = true)
    List<Detail_Commande> findTop5MostExpensiveNative();

    List<Detail_Commande> findByQuantiteArticleIsNull();
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.quantiteArticle IS NULL")
    List<Detail_Commande> findByQuantiteArticleIsNullJPQL();
    @Query(value = "SELECT * FROM detail_commande WHERE quantite_article IS NULL", nativeQuery = true)
    List<Detail_Commande> findByQuantiteArticleIsNullNative();

    List<Detail_Commande> findBySousTotalDetailArticleApresPromoIsNotNull();
    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticleApresPromo IS NOT NULL")
    List<Detail_Commande> findBySousTotalApresPromoIsNotNullJPQL();
    @Query(value = "SELECT * FROM detail_commande WHERE sous_total_detail_article_apres_promo IS NOT NULL", nativeQuery = true)
    List<Detail_Commande> findBySousTotalApresPromoIsNotNullNative();

    @Query("SELECT DISTINCT dc FROM Detail_Commande dc LEFT JOIN FETCH dc.commande LEFT JOIN FETCH dc.article")
    List<Detail_Commande> findAllWithCommandeAndArticleJPQL();
    @Query(value = "SELECT DISTINCT dc.* FROM detail_commande dc " +
           "LEFT JOIN commande c ON dc.id_commande = c.id_commande " +
           "LEFT JOIN article a ON dc.id_article = a.id_article", nativeQuery = true)
    List<Detail_Commande> findAllWithCommandeAndArticleNative();


    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.quantiteArticle <> :quantite")
    List<Detail_Commande> findByQuantiteArticleNot(@Param("quantite") int quantite);

    List<Detail_Commande> findByQuantiteArticleGreaterThan(int quantite);

    List<Detail_Commande> findByQuantiteArticleLessThan(int quantite);

    List<Detail_Commande> findByQuantiteArticleBetween(int minQuantite, int maxQuantite);

    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.quantiteArticle NOT BETWEEN :minQuantite AND :maxQuantite")
    List<Detail_Commande> findByQuantiteArticleNotBetween(@Param("minQuantite") int minQuantite, @Param("maxQuantite") int maxQuantite);

    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticle <> :sousTotal")
    List<Detail_Commande> findBySousTotalDetailArticleNot(@Param("sousTotal") float sousTotal);

    List<Detail_Commande> findBySousTotalDetailArticleGreaterThan(float sousTotal);

    List<Detail_Commande> findBySousTotalDetailArticleLessThan(float sousTotal);

    List<Detail_Commande> findBySousTotalDetailArticleBetween(float minSousTotal, float maxSousTotal);

    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticle NOT BETWEEN :minSousTotal AND :maxSousTotal")
    List<Detail_Commande> findBySousTotalDetailArticleNotBetween(@Param("minSousTotal") float minSousTotal, @Param("maxSousTotal") float maxSousTotal);

    List<Detail_Commande> findBySousTotalDetailArticleApresPromoGreaterThan(float sousTotal);

    List<Detail_Commande> findBySousTotalDetailArticleApresPromoLessThan(float sousTotal);

    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.commande.idCommande = :commandeId")
    List<Detail_Commande> findByCommandeId(@Param("commandeId") Long commandeId);

    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.article.idArticle = :articleId")
    List<Detail_Commande> findByArticleId(@Param("articleId") Long articleId);

    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.article.nomArticle = :nomArticle")
    List<Detail_Commande> findByArticleNom(@Param("nomArticle") String nomArticle);

    @Query("SELECT dc FROM Detail_Commande dc WHERE LOWER(dc.article.nomArticle) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Detail_Commande> findByArticleNomContaining(@Param("keyword") String keyword);

    @Query("SELECT dc FROM Detail_Commande dc WHERE LOWER(dc.article.nomArticle) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Detail_Commande> findByArticleNomStartingWith(@Param("prefix") String prefix);

    @Query("SELECT dc FROM Detail_Commande dc WHERE LOWER(dc.article.nomArticle) LIKE LOWER(CONCAT('%', :suffix))")
    List<Detail_Commande> findByArticleNomEndingWith(@Param("suffix") String suffix);

    @Query("SELECT dc FROM Detail_Commande dc WHERE LOWER(dc.article.nomArticle) NOT LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Detail_Commande> findByArticleNomNotContaining(@Param("keyword") String keyword);

    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticleApresPromo < dc.sousTotalDetailArticle")
    List<Detail_Commande> findDetailsWithDiscount();

    @Query("SELECT dc FROM Detail_Commande dc WHERE dc.sousTotalDetailArticleApresPromo = dc.sousTotalDetailArticle")
    List<Detail_Commande> findDetailsWithoutDiscount();

    @Query("SELECT SUM(dc.quantiteArticle) FROM Detail_Commande dc WHERE dc.article.idArticle = :articleId")
    Integer calculateTotalQuantitySoldByArticle(@Param("articleId") Long articleId);

    @Query("SELECT dc.article.nomArticle, SUM(dc.quantiteArticle) as totalQuantity FROM Detail_Commande dc GROUP BY dc.article.nomArticle ORDER BY totalQuantity DESC")
    List<Object[]> findMostSoldArticles();

}

