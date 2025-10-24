package tn.esprit.tpcafeziedsnoussi.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "carte_fidelite")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarteFidelite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carte_fidelite")
    private Long idCarteFidelite;

    @Column(name = "points_accumules")
    private int pointsAccumules;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    // inverse side of one-to-one with Client
    @OneToOne(mappedBy = "carteFidelite")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Client client;
}