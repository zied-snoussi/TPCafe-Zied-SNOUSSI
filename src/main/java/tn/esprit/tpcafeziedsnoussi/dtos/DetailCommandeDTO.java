package tn.esprit.tpcafeziedsnoussi.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailCommandeDTO {
    private Long idDetailCommande;
    private int quantiteArticle;
    private float sousTotalDetailArticle;
    private float sousTotalDetailArticleApresPromo;
    private Long commandeId;
    private Long articleId;
}
