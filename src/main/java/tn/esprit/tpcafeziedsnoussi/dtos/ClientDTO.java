package tn.esprit.tpcafeziedsnoussi.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
    private Long idClient;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Long adresseId;
    private Long carteFideliteId;
    private AdresseDTO adresse;
    private CarteFideliteDTO carteFidelite;
}
