package tn.esprit.tpcafeziedsnoussi.mappers;

import org.mapstruct.*;
import tn.esprit.tpcafeziedsnoussi.dtos.CommandeDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Commande;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {DetailCommandeMapper.class})
public interface CommandeMapper {

    @Mapping(source = "client.idClient", target = "clientId")
    @Mapping(target = "detailCommandeIds", expression = "java(mapDetailsToIds(commande.getDetailCommandes()))")
    @Mapping(source = "detailCommandes", target = "details")
    CommandeDTO toDTO(Commande commande);

    @Mapping(source = "details", target = "detailCommandes")
    Commande toEntity(CommandeDTO dto);

    @AfterMapping
    default void linkDetails(@MappingTarget Commande commande) {
        if (commande.getDetailCommandes() != null) {
            commande.getDetailCommandes().forEach(detail -> detail.setCommande(commande));
        }
    }

    default java.util.List<Long> mapDetailsToIds(java.util.List<tn.esprit.tpcafeziedsnoussi.entities.Detail_Commande> details) {
        if (details == null) {
            return null;
        }
        return details.stream()
                .map(tn.esprit.tpcafeziedsnoussi.entities.Detail_Commande::getIdDetailCommande)
                .collect(java.util.stream.Collectors.toList());
    }
}
