package tn.esprit.tpcafeziedsnoussi.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "carte_fidelite")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
public class CarteFidelite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carte_fidelite")
    Long idCarteFidelite;

    @Column(name = "points_accumules")
    int pointsAccumules;

    @Column(name = "date_creation")
    LocalDate dateCreation;

    // inverse side of one-to-one with Client
    @OneToOne(mappedBy = "carteFidelite")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Client client;
}