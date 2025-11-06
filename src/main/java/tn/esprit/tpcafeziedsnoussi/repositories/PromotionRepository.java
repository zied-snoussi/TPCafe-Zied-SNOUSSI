package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpcafeziedsnoussi.entities.Promotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    // Find promotions by percentage
    List<Promotion> findByPourcentagePromo(float pourcentagePromo);

    // Find promotions with percentage NOT equal
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo <> :percentage")
    List<Promotion> findByPourcentagePromoNot(@Param("percentage") float percentage);

    // Find promotions with percentage greater than
    List<Promotion> findByPourcentagePromoGreaterThan(float percentage);

    // Find promotions with percentage less than
    List<Promotion> findByPourcentagePromoLessThan(float percentage);

    // Find promotions with percentage between range
    List<Promotion> findByPourcentagePromoBetween(float minPercentage, float maxPercentage);

    // Find promotions with percentage NOT between range
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo NOT BETWEEN :minPercentage AND :maxPercentage")
    List<Promotion> findByPourcentagePromoNotBetween(@Param("minPercentage") float minPercentage, @Param("maxPercentage") float maxPercentage);

    // Find promotions by start date
    List<Promotion> findByDateDebutPromo(LocalDate dateDebutPromo);

    // Find promotions NOT starting on a specific date
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo <> :date")
    List<Promotion> findByDateDebutPromoNot(@Param("date") LocalDate date);

    // Find promotions by end date
    List<Promotion> findByDateFinPromo(LocalDate dateFinPromo);

    // Find promotions NOT ending on a specific date
    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo <> :date")
    List<Promotion> findByDateFinPromoNot(@Param("date") LocalDate date);

    // Find promotions starting after a specific date
    List<Promotion> findByDateDebutPromoAfter(LocalDate date);

    // Find promotions starting before a specific date
    List<Promotion> findByDateDebutPromoBefore(LocalDate date);

    // Find promotions ending before a specific date
    List<Promotion> findByDateFinPromoBefore(LocalDate date);

    // Find promotions ending after a specific date
    List<Promotion> findByDateFinPromoAfter(LocalDate date);

    // Find promotions between date range (start date)
    List<Promotion> findByDateDebutPromoBetween(LocalDate startDate, LocalDate endDate);

    // Find promotions NOT between date range (start date)
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo NOT BETWEEN :startDate AND :endDate")
    List<Promotion> findByDateDebutPromoNotBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Find promotions between date range (end date)
    List<Promotion> findByDateFinPromoBetween(LocalDate startDate, LocalDate endDate);

    // Custom query to find active promotions
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo <= CURRENT_DATE AND p.dateFinPromo >= CURRENT_DATE")
    List<Promotion> findActivePromotions();

    // Custom query to find inactive promotions (not active)
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo > CURRENT_DATE OR p.dateFinPromo < CURRENT_DATE")
    List<Promotion> findInactivePromotions();

    // Custom query to find upcoming promotions
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo > CURRENT_DATE")
    List<Promotion> findUpcomingPromotions();

    // Custom query to find expired promotions
    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo < CURRENT_DATE")
    List<Promotion> findExpiredPromotions();

    // Custom query to find promotions by article ID
    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE a.idArticle = :articleId")
    List<Promotion> findByArticleId(@Param("articleId") Long articleId);

    // Custom query to find promotions by article name
    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE a.nomArticle = :nomArticle")
    List<Promotion> findByArticleNom(@Param("nomArticle") String nomArticle);

    // Custom query to find promotions by article name containing (ignore case)
    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Promotion> findByArticleNomContaining(@Param("keyword") String keyword);

    // Custom query to find promotions by article name starting with
    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Promotion> findByArticleNomStartingWith(@Param("prefix") String prefix);

    // Custom query to find promotions by article name ending with
    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :suffix))")
    List<Promotion> findByArticleNomEndingWith(@Param("suffix") String suffix);

    // Custom query to find promotions by article name NOT containing
    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE LOWER(a.nomArticle) NOT LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Promotion> findByArticleNomNotContaining(@Param("keyword") String keyword);

    // Custom query to find active promotions for a specific article
    @Query("SELECT p FROM Promotion p JOIN p.articles a WHERE a.idArticle = :articleId AND p.dateDebutPromo <= CURRENT_DATE AND p.dateFinPromo >= CURRENT_DATE")
    List<Promotion> findActivePromotionsByArticleId(@Param("articleId") Long articleId);

}
