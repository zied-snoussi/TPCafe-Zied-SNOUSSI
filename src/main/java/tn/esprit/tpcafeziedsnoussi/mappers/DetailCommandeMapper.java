package tn.esprit.tpcafeziedsnoussi.mappers;

import org.springframework.stereotype.Component;
import tn.esprit.tpcafeziedsnoussi.dtos.DetailCommandeDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Detail_Commande;

@Component
public class DetailCommandeMapper {

    public DetailCommandeDTO toDTO(Detail_Commande detailCommande) {
        if (detailCommande == null) return null;
        
        return DetailCommandeDTO.builder()
                .idDetailCommande(detailCommande.getIdDetailCommande())
                .quantiteArticle(detailCommande.getQuantiteArticle())
                .sousTotalDetailArticle(detailCommande.getSousTotalDetailArticle())
                .sousTotalDetailArticleApresPromo(detailCommande.getSousTotalDetailArticleApresPromo())
                .commandeId(detailCommande.getCommande() != null ? detailCommande.getCommande().getIdCommande() : null)
                .articleId(detailCommande.getArticle() != null ? detailCommande.getArticle().getIdArticle() : null)
                .build();
    }

    public Detail_Commande toEntity(DetailCommandeDTO dto) {
        if (dto == null) return null;
        
        return Detail_Commande.builder()
                .idDetailCommande(dto.getIdDetailCommande())
                .quantiteArticle(dto.getQuantiteArticle())
                .sousTotalDetailArticle(dto.getSousTotalDetailArticle())
                .sousTotalDetailArticleApresPromo(dto.getSousTotalDetailArticleApresPromo())
                .build();
    }
}
