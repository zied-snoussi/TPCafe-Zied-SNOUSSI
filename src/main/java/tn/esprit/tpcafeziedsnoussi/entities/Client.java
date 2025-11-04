package tn.esprit.tpcafeziedsnoussi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    Long idClient;

    @Column(name = "nom")
    String nom;

    @Column(name = "prenom")
    String prenom;

    @Column(name = "date_naissance")
    LocalDate dateNaissance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresse", referencedColumnName = "id_adresse")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Adresse adresse;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carte_fidelite", referencedColumnName = "id_carte_fidelite")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    CarteFidelite carteFidelite;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    List<Commande> commandes = new ArrayList<>();
}