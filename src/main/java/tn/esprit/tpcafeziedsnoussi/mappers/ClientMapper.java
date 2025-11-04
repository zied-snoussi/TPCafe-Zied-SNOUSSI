package tn.esprit.tpcafeziedsnoussi.mappers;

import org.springframework.stereotype.Component;
import tn.esprit.tpcafeziedsnoussi.dtos.ClientDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Client;

@Component
public class ClientMapper {

    public ClientDTO toDTO(Client client) {
        if (client == null) return null;
        
        return ClientDTO.builder()
                .idClient(client.getIdClient())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .dateNaissance(client.getDateNaissance())
                .adresseId(client.getAdresse() != null ? client.getAdresse().getIdAdresse() : null)
                .carteFideliteId(client.getCarteFidelite() != null ? client.getCarteFidelite().getIdCarteFidelite() : null)
                .build();
    }

    public Client toEntity(ClientDTO dto) {
        if (dto == null) return null;
        
        return Client.builder()
                .idClient(dto.getIdClient())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .dateNaissance(dto.getDateNaissance())
                .build();
    }
}
