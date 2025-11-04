package tn.esprit.tpcafeziedsnoussi.mappers;

import org.springframework.stereotype.Component;
import tn.esprit.tpcafeziedsnoussi.dtos.CommandeDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Commande;

import java.util.stream.Collectors;

@Component
public class CommandeMapper {

    public CommandeDTO toDTO(Commande commande) {
        if (commande == null) return null;
        
        return CommandeDTO.builder()
                .idCommande(commande.getIdCommande())
                .dateCommande(commande.getDateCommande())
                .totalCommande(commande.getTotalCommande())
                .statusCommande(commande.getStatusCommande())
                .clientId(commande.getClient() != null ? commande.getClient().getIdClient() : null)
                .detailCommandeIds(commande.getDetailCommandes() != null ? 
                    commande.getDetailCommandes().stream()
                        .map(dc -> dc.getIdDetailCommande())
                        .collect(Collectors.toList()) : null)
                .build();
    }

    public Commande toEntity(CommandeDTO dto) {
        if (dto == null) return null;
        
        return Commande.builder()
                .idCommande(dto.getIdCommande())
                .dateCommande(dto.getDateCommande())
                .totalCommande(dto.getTotalCommande())
                .statusCommande(dto.getStatusCommande())
                .build();
    }
}
