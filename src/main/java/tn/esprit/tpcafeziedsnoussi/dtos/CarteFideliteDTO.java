package tn.esprit.tpcafeziedsnoussi.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteFideliteDTO {
    private Long idCarteFidelite;
    private int pointsAccumules;
    private LocalDate dateCreation;
    private Long clientId;
}
