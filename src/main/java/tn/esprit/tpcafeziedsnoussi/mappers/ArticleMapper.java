package tn.esprit.tpcafeziedsnoussi.mappers;

import org.mapstruct.Mapper;
import tn.esprit.tpcafeziedsnoussi.dtos.ArticleDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Article;

@Mapper(componentModel = "spring", uses = {PromotionMapper.class})
public interface ArticleMapper {

    ArticleDTO toDTO(Article article);

    Article toEntity(ArticleDTO dto);
}
