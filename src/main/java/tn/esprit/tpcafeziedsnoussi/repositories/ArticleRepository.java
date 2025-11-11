package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpcafeziedsnoussi.entities.Article;
import tn.esprit.tpcafeziedsnoussi.enums.TypeArticle;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {


    List<Article> findByNomArticle(String nomArticle);
    @Query("SELECT a FROM Article a WHERE a.nomArticle = :nomArticle")
    List<Article> findByNomArticleJPQL(@Param("nomArticle") String nomArticle);
    @Query(value = "SELECT * FROM article WHERE nom_article = :nomArticle", nativeQuery = true)
    List<Article> findByNomArticleNative(@Param("nomArticle") String nomArticle);

    List<Article> findByTypeArticle(TypeArticle typeArticle);
    @Query("SELECT a FROM Article a WHERE a.typeArticle = :typeArticle")
    List<Article> findByTypeArticleJPQL(@Param("typeArticle") TypeArticle typeArticle);
    @Query(value = "SELECT * FROM article WHERE type_article = :typeArticle", nativeQuery = true)
    List<Article> findByTypeArticleNative(@Param("typeArticle") String typeArticle);

    List<Article> findByPrixArticle(float prixArticle);
    @Query("SELECT a FROM Article a WHERE a.prixArticle = :prixArticle")
    List<Article> findByPrixArticleJPQL(@Param("prixArticle") float prixArticle);
    @Query(value = "SELECT * FROM article WHERE prix_article = :prixArticle", nativeQuery = true)
    List<Article> findByPrixArticleNative(@Param("prixArticle") float prixArticle);

    boolean existsByNomArticle(String nomArticle);
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Article a WHERE a.nomArticle = :nomArticle")
    boolean existsByNomArticleJPQL(@Param("nomArticle") String nomArticle);
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM article WHERE nom_article = :nomArticle", nativeQuery = true)
    int existsByNomArticleNative(@Param("nomArticle") String nomArticle);

    long countByTypeArticle(TypeArticle typeArticle);
    @Query("SELECT COUNT(a) FROM Article a WHERE a.typeArticle = :typeArticle")
    long countByTypeArticleJPQL(@Param("typeArticle") TypeArticle typeArticle);
    @Query(value = "SELECT COUNT(*) FROM article WHERE type_article = :typeArticle", nativeQuery = true)
    long countByTypeArticleNative(@Param("typeArticle") String typeArticle);

    List<Article> findByNomArticleContainingIgnoreCaseAndTypeArticle(String nomArticle, TypeArticle typeArticle);
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :nomArticle, '%')) AND a.typeArticle = :typeArticle")
    List<Article> findByNomArticleContainingAndTypeArticleJPQL(@Param("nomArticle") String nomArticle, @Param("typeArticle") TypeArticle typeArticle);
    @Query(value = "SELECT * FROM article WHERE LOWER(nom_article) LIKE LOWER(CONCAT('%', :nomArticle, '%')) AND type_article = :typeArticle", nativeQuery = true)
    List<Article> findByNomArticleContainingAndTypeArticleNative(@Param("nomArticle") String nomArticle, @Param("typeArticle") String typeArticle);

    List<Article> findByPrixArticleBetweenAndTypeArticleIn(float minPrix, float maxPrix, List<TypeArticle> types);
    @Query("SELECT a FROM Article a WHERE a.prixArticle BETWEEN :minPrix AND :maxPrix AND a.typeArticle IN :types")
    List<Article> findByPrixArticleBetweenAndTypeArticleInJPQL(@Param("minPrix") float minPrix, @Param("maxPrix") float maxPrix, @Param("types") List<TypeArticle> types);
    @Query(value = "SELECT * FROM article WHERE prix_article BETWEEN :minPrix AND :maxPrix AND type_article IN :types", nativeQuery = true)
    List<Article> findByPrixArticleBetweenAndTypeArticleInNative(@Param("minPrix") float minPrix, @Param("maxPrix") float maxPrix, @Param("types") List<String> types);

    List<Article> findByNomArticleStartingWithIgnoreCaseOrderByPrixArticleAsc(String prefix);
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT(:prefix, '%')) ORDER BY a.prixArticle ASC")
    List<Article> findByNomArticleStartingWithOrderByPrixArticleJPQL(@Param("prefix") String prefix);
    @Query(value = "SELECT * FROM article WHERE LOWER(nom_article) LIKE LOWER(CONCAT(:prefix, '%')) ORDER BY prix_article ASC", nativeQuery = true)
    List<Article> findByNomArticleStartingWithOrderByPrixArticleNative(@Param("prefix") String prefix);

    List<Article> findByTypeArticleAndPrixArticleLessThan(TypeArticle typeArticle, float prix);
    @Query("SELECT a FROM Article a WHERE a.typeArticle = :typeArticle AND a.prixArticle < :prix")
    List<Article> findByTypeArticleAndPrixArticleLessThanJPQL(@Param("typeArticle") TypeArticle typeArticle, @Param("prix") float prix);
    @Query(value = "SELECT * FROM article WHERE type_article = :typeArticle AND prix_article < :prix", nativeQuery = true)
    List<Article> findByTypeArticleAndPrixArticleLessThanNative(@Param("typeArticle") String typeArticle, @Param("prix") float prix);

    List<Article> findByNomArticleContainingIgnoreCaseOrTypeArticleOrderByPrixArticleDesc(String nomArticle, TypeArticle typeArticle);
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :nomArticle, '%')) OR a.typeArticle = :typeArticle ORDER BY a.prixArticle DESC")
    List<Article> findByNomArticleOrTypeArticleOrderByPrixArticleDescJPQL(@Param("nomArticle") String nomArticle, @Param("typeArticle") TypeArticle typeArticle);
    @Query(value = "SELECT * FROM article WHERE LOWER(nom_article) LIKE LOWER(CONCAT('%', :nomArticle, '%')) OR type_article = :typeArticle ORDER BY prix_article DESC", nativeQuery = true)
    List<Article> findByNomArticleOrTypeArticleOrderByPrixArticleDescNative(@Param("nomArticle") String nomArticle, @Param("typeArticle") String typeArticle);

    List<Article> findByNomArticleStartingWithIgnoreCase(String prefix);
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Article> findByNomArticleStartingWithJPQL(@Param("prefix") String prefix);
    @Query(value = "SELECT * FROM article WHERE LOWER(nom_article) LIKE LOWER(CONCAT(:prefix, '%'))", nativeQuery = true)
    List<Article> findByNomArticleStartingWithNative(@Param("prefix") String prefix);

    List<Article> findByNomArticleEndingWithIgnoreCase(String suffix);
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :suffix))")
    List<Article> findByNomArticleEndingWithJPQL(@Param("suffix") String suffix);
    @Query(value = "SELECT * FROM article WHERE LOWER(nom_article) LIKE LOWER(CONCAT('%', :suffix))", nativeQuery = true)
    List<Article> findByNomArticleEndingWithNative(@Param("suffix") String suffix);

    List<Article> findByTypeArticleIsNull();
    @Query("SELECT a FROM Article a WHERE a.typeArticle IS NULL")
    List<Article> findByTypeArticleIsNullJPQL();
    @Query(value = "SELECT * FROM article WHERE type_article IS NULL", nativeQuery = true)
    List<Article> findByTypeArticleIsNullNative();

    List<Article> findByPrixArticleIsNotNull();
    @Query("SELECT a FROM Article a WHERE a.prixArticle IS NOT NULL")
    List<Article> findByPrixArticleIsNotNullJPQL();
    @Query(value = "SELECT * FROM article WHERE prix_article IS NOT NULL", nativeQuery = true)
    List<Article> findByPrixArticleIsNotNullNative();

    @Query("SELECT DISTINCT a FROM Article a JOIN FETCH a.promotions p WHERE p.dateDebutPromo <= CURRENT_DATE AND p.dateFinPromo >= CURRENT_DATE")
    List<Article> findArticlesWithActivePromotionsJPQL();
    @Query(value = "SELECT DISTINCT a.* FROM article a " +
           "INNER JOIN article_promotion ap ON a.id_article = ap.id_article " +
           "INNER JOIN promotion p ON ap.id_promotion = p.id_promotion " +
           "WHERE p.date_debut_promo <= CURRENT_DATE AND p.date_fin_promo >= CURRENT_DATE", nativeQuery = true)
    List<Article> findArticlesWithActivePromotionsNative();

    List<Article> findByNomArticleContainingIgnoreCaseAndPrixArticleBetween(String nomArticle, float minPrix, float maxPrix);
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :nomArticle, '%')) AND a.prixArticle BETWEEN :minPrix AND :maxPrix")
    List<Article> findByNomArticleContainingAndPrixArticleBetweenJPQL(@Param("nomArticle") String nomArticle, @Param("minPrix") float minPrix, @Param("maxPrix") float maxPrix);
    @Query(value = "SELECT * FROM article WHERE LOWER(nom_article) LIKE LOWER(CONCAT('%', :nomArticle, '%')) AND prix_article BETWEEN :minPrix AND :maxPrix", nativeQuery = true)
    List<Article> findByNomArticleContainingAndPrixArticleBetweenNative(@Param("nomArticle") String nomArticle, @Param("minPrix") float minPrix, @Param("maxPrix") float maxPrix);


    List<Article> findByNomArticleContainingIgnoreCase(String nomArticle);

    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) NOT LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Article> findByNomArticleNotContaining(@Param("keyword") String keyword);

    @Query("SELECT a FROM Article a WHERE a.nomArticle NOT LIKE :prefix%")
    List<Article> findByNomArticleNotStartingWith(@Param("prefix") String prefix);

    @Query("SELECT a FROM Article a WHERE a.typeArticle <> :typeArticle")
    List<Article> findByTypeArticleNot(@Param("typeArticle") TypeArticle typeArticle);

    @Query("SELECT a FROM Article a WHERE a.prixArticle <> :prix")
    List<Article> findByPrixArticleNot(@Param("prix") float prix);

    List<Article> findByPrixArticleLessThan(float prix);

    List<Article> findByPrixArticleGreaterThan(float prix);

    List<Article> findByPrixArticleBetween(float minPrix, float maxPrix);

    @Query("SELECT a FROM Article a WHERE a.prixArticle NOT BETWEEN :minPrix AND :maxPrix")
    List<Article> findByPrixArticleNotBetween(@Param("minPrix") float minPrix, @Param("maxPrix") float maxPrix);

    @Query("SELECT DISTINCT a FROM Article a JOIN a.promotions p WHERE p.dateDebutPromo <= CURRENT_DATE AND p.dateFinPromo >= CURRENT_DATE")
    List<Article> findArticlesWithActivePromotions();

    @Query("SELECT a FROM Article a JOIN a.promotions p WHERE p.pourcentagePromo >= :percentage")
    List<Article> findByPromotionPercentageGreaterThan(@Param("percentage") float percentage);

}
