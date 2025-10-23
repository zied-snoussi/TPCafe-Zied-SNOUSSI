package tn.esprit.tpcafeziedsnoussi.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.tpcafeziedsnoussi.enums.StatusCommande;

import java.time.LocalDate;

@Entity
@Table(name = "commande")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commande")
    private Long idCommande;

    @Column(name = "date_commande")
    private LocalDate dateCommande;

    @Column(name = "total_commande")
    private float totalCommande;

    @Column(name = "status_commande")
    @Enumerated(EnumType.STRING)
    private StatusCommande statusCommande;
}