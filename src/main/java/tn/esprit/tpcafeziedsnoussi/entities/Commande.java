package tn.esprit.tpcafeziedsnoussi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tpcafeziedsnoussi.enums.StatusCommande;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commande")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commande")
    Long idCommande;

    @Column(name = "date_commande")
    LocalDate dateCommande;

    @Column(name = "total_commande")
    float totalCommande;

    @Column(name = "status_commande")
    @Enumerated(EnumType.STRING)
    StatusCommande statusCommande;

    // Relation to Client (many commandes can belong to one client)
    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Client client;

    // One commande has many detail_commande
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    List<Detail_Commande> detailCommandes = new ArrayList<>();
}