package tn.esprit.tpcafeziedsnoussi.dtos;

import lombok.*;
import tn.esprit.tpcafeziedsnoussi.enums.StatusCommande;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeDTO {
    private Long idCommande;
    private LocalDate dateCommande;
    private float totalCommande;
    private StatusCommande statusCommande;
    private Long clientId;
    private List<Long> detailCommandeIds;
    private List<DetailCommandeDTO> details;
}
