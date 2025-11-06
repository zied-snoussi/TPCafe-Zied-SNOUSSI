package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpcafeziedsnoussi.entities.Article;
import tn.esprit.tpcafeziedsnoussi.enums.TypeArticle;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // Find articles by name
    List<Article> findByNomArticle(String nomArticle);

    // Find articles by name containing (case-insensitive)
    List<Article> findByNomArticleContainingIgnoreCase(String nomArticle);

    // Find articles by name starting with (ignore case)
    List<Article> findByNomArticleStartingWithIgnoreCase(String prefix);

    // Find articles by name ending with (ignore case)
    List<Article> findByNomArticleEndingWithIgnoreCase(String suffix);

    // Find articles by name NOT containing
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) NOT LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Article> findByNomArticleNotContaining(@Param("keyword") String keyword);

    // Find articles by name NOT starting with
    @Query("SELECT a FROM Article a WHERE a.nomArticle NOT LIKE :prefix%")
    List<Article> findByNomArticleNotStartingWith(@Param("prefix") String prefix);

    // Find articles by type
    List<Article> findByTypeArticle(TypeArticle typeArticle);

    // Find articles NOT of a specific type
    @Query("SELECT a FROM Article a WHERE a.typeArticle <> :typeArticle")
    List<Article> findByTypeArticleNot(@Param("typeArticle") TypeArticle typeArticle);

    // Find articles by price
    List<Article> findByPrixArticle(float prixArticle);

    // Find articles with price NOT equal
    @Query("SELECT a FROM Article a WHERE a.prixArticle <> :prix")
    List<Article> findByPrixArticleNot(@Param("prix") float prix);

    // Find articles with price less than
    List<Article> findByPrixArticleLessThan(float prix);

    // Find articles with price greater than
    List<Article> findByPrixArticleGreaterThan(float prix);

    // Find articles with price between range
    List<Article> findByPrixArticleBetween(float minPrix, float maxPrix);

    // Find articles with price NOT between range
    @Query("SELECT a FROM Article a WHERE a.prixArticle NOT BETWEEN :minPrix AND :maxPrix")
    List<Article> findByPrixArticleNotBetween(@Param("minPrix") float minPrix, @Param("maxPrix") float maxPrix);

    // Find articles by type and price less than
    List<Article> findByTypeArticleAndPrixArticleLessThan(TypeArticle typeArticle, float prix);

    // Custom query to find articles with active promotions
    @Query("SELECT DISTINCT a FROM Article a JOIN a.promotions p WHERE p.dateDebutPromo <= CURRENT_DATE AND p.dateFinPromo >= CURRENT_DATE")
    List<Article> findArticlesWithActivePromotions();

    // Custom query to find articles by promotion percentage
    @Query("SELECT a FROM Article a JOIN a.promotions p WHERE p.pourcentagePromo >= :percentage")
    List<Article> findByPromotionPercentageGreaterThan(@Param("percentage") float percentage);

}
