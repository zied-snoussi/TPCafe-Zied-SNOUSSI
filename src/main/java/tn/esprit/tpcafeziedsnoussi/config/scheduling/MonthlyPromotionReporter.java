package tn.esprit.tpcafeziedsnoussi.config.scheduling;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpcafeziedsnoussi.entities.Promotion;
import tn.esprit.tpcafeziedsnoussi.entities.Article;
import tn.esprit.tpcafeziedsnoussi.repositories.PromotionRepository;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class MonthlyPromotionReporter {

    private final PromotionRepository promotionRepository;
    private final SchedulingProperties schedulingProperties;

    /**
     * Runs at the beginning of each month (configurable cron) and logs promotions active for the current month
     */
    @Scheduled(cron = "${scheduling.monthly-promo-cron}")
    @Transactional(readOnly = true)
    public void reportMonthlyPromotions() {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int year = today.getYear();

        List<Promotion> promotions = promotionRepository.findPromotionsActiveInMonthWithArticles(month, year);
        if (promotions == null || promotions.isEmpty()) {
            log.info("[MonthlyPromo] No promotions active for month {}/{}", month, year);
            return;
        }

        log.info("[MonthlyPromo] Found {} promotions active for {}/{}:", promotions.size(), month, year);
        for (Promotion promo : promotions) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Promo id=%d pourcentage=%.2f dateDebut=%s dateFin=%s | Articles: ",
                    promo.getIdPromotion(), promo.getPourcentagePromo(), promo.getDateDebutPromo(), promo.getDateFinPromo()));
            boolean first = true;
            for (Article a : promo.getArticles()) {
                if (!first) sb.append(", ");
                sb.append(String.format("[%d] %s", a.getIdArticle(), a.getNomArticle()));
                first = false;
            }
            log.info(sb.toString());
        }
    }
}

