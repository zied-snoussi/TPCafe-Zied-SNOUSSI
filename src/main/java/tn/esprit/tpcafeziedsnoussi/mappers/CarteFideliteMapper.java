package tn.esprit.tpcafeziedsnoussi.mappers;

import org.springframework.stereotype.Component;
import tn.esprit.tpcafeziedsnoussi.dtos.CarteFideliteDTO;
import tn.esprit.tpcafeziedsnoussi.entities.CarteFidelite;

@Component
public class CarteFideliteMapper {

    public CarteFideliteDTO toDTO(CarteFidelite carteFidelite) {
        if (carteFidelite == null) return null;
        
        return CarteFideliteDTO.builder()
                .idCarteFidelite(carteFidelite.getIdCarteFidelite())
                .pointsAccumules(carteFidelite.getPointsAccumules())
                .dateCreation(carteFidelite.getDateCreation())
                .clientId(carteFidelite.getClient() != null ? carteFidelite.getClient().getIdClient() : null)
                .build();
    }

    public CarteFidelite toEntity(CarteFideliteDTO dto) {
        if (dto == null) return null;
        
        return CarteFidelite.builder()
                .idCarteFidelite(dto.getIdCarteFidelite())
                .pointsAccumules(dto.getPointsAccumules())
                .dateCreation(dto.getDateCreation())
                .build();
    }
}
