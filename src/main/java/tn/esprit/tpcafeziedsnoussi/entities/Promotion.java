package tn.esprit.tpcafeziedsnoussi.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "promotion")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promotion")
    Long idPromotion;

    @Column(name = "pourcentage_promo")
    float pourcentagePromo;

    @Column(name = "date_debut_promo")
    LocalDate dateDebutPromo;

    @Column(name = "date_fin_promo")
    LocalDate dateFinPromo;

    // inverse side of many-to-many with Article
    @ManyToMany(mappedBy = "promotions")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Article> articles = new ArrayList<>();
}