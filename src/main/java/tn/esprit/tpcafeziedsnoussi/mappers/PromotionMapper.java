package tn.esprit.tpcafeziedsnoussi.mappers;

import org.springframework.stereotype.Component;
import tn.esprit.tpcafeziedsnoussi.dtos.PromotionDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Promotion;

import java.util.stream.Collectors;

@Component
public class PromotionMapper {

    public PromotionDTO toDTO(Promotion promotion) {
        if (promotion == null) return null;
        
        return PromotionDTO.builder()
                .idPromotion(promotion.getIdPromotion())
                .pourcentagePromo(promotion.getPourcentagePromo())
                .dateDebutPromo(promotion.getDateDebutPromo())
                .dateFinPromo(promotion.getDateFinPromo())
                .articleIds(promotion.getArticles() != null ? 
                    promotion.getArticles().stream()
                        .map(article -> article.getIdArticle())
                        .collect(Collectors.toList()) : null)
                .build();
    }

    public Promotion toEntity(PromotionDTO dto) {
        if (dto == null) return null;
        
        return Promotion.builder()
                .idPromotion(dto.getIdPromotion())
                .pourcentagePromo(dto.getPourcentagePromo())
                .dateDebutPromo(dto.getDateDebutPromo())
                .dateFinPromo(dto.getDateFinPromo())
                .build();
    }
}
