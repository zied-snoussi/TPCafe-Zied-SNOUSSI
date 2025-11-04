package tn.esprit.tpcafeziedsnoussi.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionDTO {
    private Long idPromotion;
    private float pourcentagePromo;
    private LocalDate dateDebutPromo;
    private LocalDate dateFinPromo;
    private List<Long> articleIds;
}
