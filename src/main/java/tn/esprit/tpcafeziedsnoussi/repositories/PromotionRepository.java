package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpcafeziedsnoussi.entities.Promotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {


    List<Promotion> findByPourcentagePromo(float pourcentagePromo);
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo = :pourcentagePromo")
    List<Promotion> findByPourcentagePromoJPQL(@Param("pourcentagePromo") float pourcentagePromo);
    @Query(value = "SELECT * FROM promotion WHERE pourcentage_promo = :pourcentagePromo", nativeQuery = true)
    List<Promotion> findByPourcentagePromoNative(@Param("pourcentagePromo") float pourcentagePromo);

    List<Promotion> findByDateDebutPromo(LocalDate dateDebutPromo);
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo = :dateDebutPromo")
    List<Promotion> findByDateDebutPromoJPQL(@Param("dateDebutPromo") LocalDate dateDebutPromo);
    @Query(value = "SELECT * FROM promotion WHERE date_debut_promo = :dateDebutPromo", nativeQuery = true)
    List<Promotion> findByDateDebutPromoNative(@Param("dateDebutPromo") LocalDate dateDebutPromo);

    List<Promotion> findByDateFinPromo(LocalDate dateFinPromo);
    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo = :dateFinPromo")
    List<Promotion> findByDateFinPromoJPQL(@Param("dateFinPromo") LocalDate dateFinPromo);
    @Query(value = "SELECT * FROM promotion WHERE date_fin_promo = :dateFinPromo", nativeQuery = true)
    List<Promotion> findByDateFinPromoNative(@Param("dateFinPromo") LocalDate dateFinPromo);

    boolean existsByPourcentagePromo(float pourcentagePromo);
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Promotion p WHERE p.pourcentagePromo = :pourcentagePromo")
    boolean existsByPourcentagePromoJPQL(@Param("pourcentagePromo") float pourcentagePromo);
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM promotion WHERE pourcentage_promo = :pourcentagePromo", nativeQuery = true)
    int existsByPourcentagePromoNative(@Param("pourcentagePromo") float pourcentagePromo);

    long countByDateDebutPromoAfter(LocalDate date);
    @Query("SELECT COUNT(p) FROM Promotion p WHERE p.dateDebutPromo > :date")
    long countByDateDebutPromoAfterJPQL(@Param("date") LocalDate date);
    @Query(value = "SELECT COUNT(*) FROM promotion WHERE date_debut_promo > :date", nativeQuery = true)
    long countByDateDebutPromoAfterNative(@Param("date") LocalDate date);

    List<Promotion> findByDateDebutPromoLessThanEqualAndDateFinPromoGreaterThanEqual(LocalDate date1, LocalDate date2);
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo <= :date AND p.dateFinPromo >= :date")
    List<Promotion> findActivePromotionsAtDateJPQL(@Param("date") LocalDate date);
    @Query(value = "SELECT * FROM promotion WHERE date_debut_promo <= :date AND date_fin_promo >= :date", nativeQuery = true)
    List<Promotion> findActivePromotionsAtDateNative(@Param("date") LocalDate date);

    List<Promotion> findByPourcentagePromoAndDateDebutPromoBetween(float pourcentagePromo, LocalDate startDate, LocalDate endDate);
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo = :pourcentagePromo AND p.dateDebutPromo BETWEEN :startDate AND :endDate")
    List<Promotion> findByPourcentageAndDateDebutBetweenJPQL(@Param("pourcentagePromo") float pourcentagePromo, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query(value = "SELECT * FROM promotion WHERE pourcentage_promo = :pourcentagePromo AND date_debut_promo BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Promotion> findByPourcentageAndDateDebutBetweenNative(@Param("pourcentagePromo") float pourcentagePromo, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    List<Promotion> findByPourcentagePromoInOrderByDateDebutPromoAsc(List<Float> pourcentages);
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo IN :pourcentages ORDER BY p.dateDebutPromo ASC")
    List<Promotion> findByPourcentagesOrderByDateDebutJPQL(@Param("pourcentages") List<Float> pourcentages);
    @Query(value = "SELECT * FROM promotion WHERE pourcentage_promo IN :pourcentages ORDER BY date_debut_promo ASC", nativeQuery = true)
    List<Promotion> findByPourcentagesOrderByDateDebutNative(@Param("pourcentages") List<Float> pourcentages);

    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo <= CURRENT_DATE AND p.dateFinPromo >= CURRENT_DATE ORDER BY p.pourcentagePromo DESC")
    List<Promotion> findActivePromotionsOrderByPourcentageJPQL();
    @Query(value = "SELECT * FROM promotion WHERE date_debut_promo <= CURRENT_DATE AND date_fin_promo >= CURRENT_DATE ORDER BY pourcentage_promo DESC", nativeQuery = true)
    List<Promotion> findActivePromotionsOrderByPourcentageNative();

    List<Promotion> findByDateFinPromoIsNull();
    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo IS NULL")
    List<Promotion> findByDateFinPromoIsNullJPQL();
    @Query(value = "SELECT * FROM promotion WHERE date_fin_promo IS NULL", nativeQuery = true)
    List<Promotion> findByDateFinPromoIsNullNative();

    List<Promotion> findByPourcentagePromoIsNotNull();
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo IS NOT NULL")
    List<Promotion> findByPourcentagePromoIsNotNullJPQL();
    @Query(value = "SELECT * FROM promotion WHERE pourcentage_promo IS NOT NULL", nativeQuery = true)
    List<Promotion> findByPourcentagePromoIsNotNullNative();

    @Query("SELECT DISTINCT p FROM Promotion p LEFT JOIN FETCH p.articles")
    List<Promotion> findAllWithArticlesJPQL();
    @Query(value = "SELECT DISTINCT p.* FROM promotion p " +
           "LEFT JOIN article_promotion ap ON p.id_promotion = ap.id_promotion " +
           "LEFT JOIN article a ON ap.id_article = a.id_article", nativeQuery = true)
    List<Promotion> findAllWithArticlesNative();

    List<Promotion> findByDateFinPromoBefore(LocalDate date);
    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo < :date")
    List<Promotion> findExpiredPromotionsJPQL(@Param("date") LocalDate date);
    @Query(value = "SELECT * FROM promotion WHERE date_fin_promo < :date", nativeQuery = true)
    List<Promotion> findExpiredPromotionsNative(@Param("date") LocalDate date);


    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo <> :percentage")
    List<Promotion> findByPourcentagePromoNot(@Param("percentage") float percentage);

    List<Promotion> findByPourcentagePromoGreaterThan(float percentage);

    List<Promotion> findByPourcentagePromoLessThan(float percentage);

    List<Promotion> findByPourcentagePromoBetween(float minPercentage, float maxPercentage);

    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo NOT BETWEEN :minPercentage AND :maxPercentage")
    List<Promotion> findByPourcentagePromoNotBetween(@Param("minPercentage") float minPercentage, @Param("maxPercentage") float maxPercentage);

    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo <> :date")
    List<Promotion> findByDateDebutPromoNot(@Param("date") LocalDate date);

    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo <> :date")
    List<Promotion> findByDateFinPromoNot(@Param("date") LocalDate date);

    List<Promotion> findByDateDebutPromoAfter(LocalDate date);

    List<Promotion> findByDateDebutPromoBefore(LocalDate date);

    List<Promotion> findByDateFinPromoAfter(LocalDate date);

    List<Promotion> findByDateDebutPromoBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo NOT BETWEEN :startDate AND :endDate")
    List<Promotion> findByDateDebutPromoNotBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Promotion> findByDateFinPromoBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo <= CURRENT_DATE AND p.dateFinPromo >= CURRENT_DATE")
    List<Promotion> findActivePromotions();

    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo > CURRENT_DATE OR p.dateFinPromo < CURRENT_DATE")
    List<Promotion> findInactivePromotions();

    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo > CURRENT_DATE")
    List<Promotion> findUpcomingPromotions();

    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo < CURRENT_DATE")
    List<Promotion> findExpiredPromotions();

    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE a.idArticle = :articleId")
    List<Promotion> findByArticleId(@Param("articleId") Long articleId);

    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE a.nomArticle = :nomArticle")
    List<Promotion> findByArticleNom(@Param("nomArticle") String nomArticle);

    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Promotion> findByArticleNomContaining(@Param("keyword") String keyword);

    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Promotion> findByArticleNomStartingWith(@Param("prefix") String prefix);

    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :suffix))")
    List<Promotion> findByArticleNomEndingWith(@Param("suffix") String suffix);

    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE LOWER(a.nomArticle) NOT LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Promotion> findByArticleNomNotContaining(@Param("keyword") String keyword);

    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE a.idArticle = :articleId AND p.dateDebutPromo <= CURRENT_DATE AND p.dateFinPromo >= CURRENT_DATE")
    List<Promotion> findActivePromotionsByArticleId(@Param("articleId") Long articleId);

    @Query("SELECT DISTINCT p FROM Promotion p LEFT JOIN FETCH p.articles a WHERE FUNCTION('MONTH', p.dateDebutPromo) <= :month AND FUNCTION('MONTH', p.dateFinPromo) >= :month AND FUNCTION('YEAR', p.dateDebutPromo) <= :year AND FUNCTION('YEAR', p.dateFinPromo) >= :year")
    List<Promotion> findPromotionsActiveInMonthWithArticles(@Param("month") int month, @Param("year") int year);

}
