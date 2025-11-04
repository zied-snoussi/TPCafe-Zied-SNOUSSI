package tn.esprit.tpcafeziedsnoussi.mappers;

import org.springframework.stereotype.Component;
import tn.esprit.tpcafeziedsnoussi.dtos.AdresseDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Adresse;

@Component
public class AdresseMapper {

    public AdresseDTO toDTO(Adresse adresse) {
        if (adresse == null) return null;
        
        return AdresseDTO.builder()
                .idAdresse(adresse.getIdAdresse())
                .rue(adresse.getRue())
                .ville(adresse.getVille())
                .codePostal(adresse.getCodePostal())
                .clientId(adresse.getClient() != null ? adresse.getClient().getIdClient() : null)
                .build();
    }

    public Adresse toEntity(AdresseDTO dto) {
        if (dto == null) return null;
        
        return Adresse.builder()
                .idAdresse(dto.getIdAdresse())
                .rue(dto.getRue())
                .ville(dto.getVille())
                .codePostal(dto.getCodePostal())
                .build();
    }
}
