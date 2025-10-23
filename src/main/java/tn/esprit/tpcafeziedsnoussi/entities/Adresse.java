package tn.esprit.tpcafeziedsnoussi.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adresse")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adresse")
    private Long idAdresse;

    @Column(name = "rue")
    private String rue;

    @Column(name = "ville")
    private String ville;

    @Column(name = "code_postal")
    private String codePostal;
}