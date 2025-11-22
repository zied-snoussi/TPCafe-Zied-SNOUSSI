package tn.esprit.tpcafeziedsnoussi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpcafeziedsnoussi.dtos.CarteFideliteDTO;
import tn.esprit.tpcafeziedsnoussi.entities.CarteFidelite;

@Mapper(componentModel = "spring")
public interface CarteFideliteMapper {

    @Mapping(source = "client.idClient", target = "clientId")
    CarteFideliteDTO toDTO(CarteFidelite carteFidelite);

    CarteFidelite toEntity(CarteFideliteDTO dto);
}
