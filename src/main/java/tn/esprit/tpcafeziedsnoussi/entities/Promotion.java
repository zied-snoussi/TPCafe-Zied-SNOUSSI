package tn.esprit.tpcafeziedsnoussi.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "promotion")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promotion")
    private Long idPromotion;

    @Column(name = "pourcentage_promo")
    private float pourcentagePromo;

    @Column(name = "date_debut_promo")
    private LocalDate dateDebutPromo;

    @Column(name = "date_fin_promo")
    private LocalDate dateFinPromo;

    // inverse side of many-to-many with Article
    @ManyToMany(mappedBy = "promotions")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Article> articles = new ArrayList<>();
}