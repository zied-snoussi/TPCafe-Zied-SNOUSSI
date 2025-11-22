package tn.esprit.tpcafeziedsnoussi.mappers;

import org.mapstruct.*;
import tn.esprit.tpcafeziedsnoussi.dtos.ClientDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Client;

@Mapper(componentModel = "spring", uses = {AdresseMapper.class, CarteFideliteMapper.class})
public interface ClientMapper {

    @Mapping(source = "adresse.idAdresse", target = "adresseId")
    @Mapping(source = "carteFidelite.idCarteFidelite", target = "carteFideliteId")
    ClientDTO toDTO(Client client);

    Client toEntity(ClientDTO dto);

    @AfterMapping
    default void linkChildren(@MappingTarget Client client) {
        if (client.getAdresse() != null) {
            client.getAdresse().setClient(client);
        }
        if (client.getCarteFidelite() != null) {
            client.getCarteFidelite().setClient(client);
        }
    }
}
