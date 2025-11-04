package tn.esprit.tpcafeziedsnoussi.mappers;

import org.springframework.stereotype.Component;
import tn.esprit.tpcafeziedsnoussi.dtos.ArticleDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Article;

@Component
public class ArticleMapper {

    public ArticleDTO toDTO(Article article) {
        if (article == null) return null;
        
        return ArticleDTO.builder()
                .idArticle(article.getIdArticle())
                .nomArticle(article.getNomArticle())
                .prixArticle(article.getPrixArticle())
                .typeArticle(article.getTypeArticle())
                .build();
    }

    public Article toEntity(ArticleDTO dto) {
        if (dto == null) return null;
        
        return Article.builder()
                .idArticle(dto.getIdArticle())
                .nomArticle(dto.getNomArticle())
                .prixArticle(dto.getPrixArticle())
                .typeArticle(dto.getTypeArticle())
                .build();
    }
}
