package tn.esprit.tpcafeziedsnoussi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "adresse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adresse")
    Long idAdresse;

    @Column(name = "rue")
    String rue;

    @Column(name = "ville")
    String ville;

    @Column(name = "code_postal")
    String codePostal;

    // inverse side of one-to-one with Client
    @OneToOne(mappedBy = "adresse")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    Client client;
}