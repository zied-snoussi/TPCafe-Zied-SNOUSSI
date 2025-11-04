package tn.esprit.tpcafeziedsnoussi.dtos;

import lombok.*;
import tn.esprit.tpcafeziedsnoussi.enums.TypeArticle;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDTO {
    private Long idArticle;
    private String nomArticle;
    private float prixArticle;
    private TypeArticle typeArticle;
}
