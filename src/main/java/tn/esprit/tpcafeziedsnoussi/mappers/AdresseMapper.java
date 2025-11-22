package tn.esprit.tpcafeziedsnoussi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpcafeziedsnoussi.dtos.AdresseDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Adresse;

@Mapper(componentModel = "spring")
public interface AdresseMapper {

    @Mapping(source = "client.idClient", target = "clientId")
    AdresseDTO toDTO(Adresse adresse);

    Adresse toEntity(AdresseDTO dto);
}
