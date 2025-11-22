package tn.esprit.tpcafeziedsnoussi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpcafeziedsnoussi.dtos.DetailCommandeDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Detail_Commande;

@Mapper(componentModel = "spring", uses = {ArticleMapper.class})
public interface DetailCommandeMapper {

    @Mapping(source = "commande.idCommande", target = "commandeId")
    @Mapping(source = "article.idArticle", target = "articleId")
    DetailCommandeDTO toDTO(Detail_Commande detailCommande);

    Detail_Commande toEntity(DetailCommandeDTO dto);
}
