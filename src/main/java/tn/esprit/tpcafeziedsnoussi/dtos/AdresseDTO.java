package tn.esprit.tpcafeziedsnoussi.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdresseDTO {
    private Long idAdresse;
    private String rue;
    private String ville;
    private String codePostal;
    private Long clientId;
}
