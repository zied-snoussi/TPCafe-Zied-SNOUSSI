package tn.esprit.tpcafeziedsnoussi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpcafeziedsnoussi.dtos.PromotionDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Promotion;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    @Mapping(target = "articleIds", expression = "java(mapArticlesToIds(promotion.getArticles()))")
    PromotionDTO toDTO(Promotion promotion);

    Promotion toEntity(PromotionDTO dto);

    default java.util.List<Long> mapArticlesToIds(java.util.List<tn.esprit.tpcafeziedsnoussi.entities.Article> articles) {
        if (articles == null) {
            return null;
        }
        return articles.stream()
                .map(tn.esprit.tpcafeziedsnoussi.entities.Article::getIdArticle)
                .collect(java.util.stream.Collectors.toList());
    }
}
