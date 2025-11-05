package tn.esprit.tpcafeziedsnoussi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "promotion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promotion")
    Long idPromotion;

    @Column(name = "pourcentage_promo")
    @DecimalMin(value = "0.0", message = "Promotion percentage must be at least 0")
    @DecimalMax(value = "100.0", message = "Promotion percentage cannot exceed 100")
    float pourcentagePromo;

    @Column(name = "date_debut_promo")
    @NotNull(message = "Promotion start date is required")
    LocalDate dateDebutPromo;

    @Column(name = "date_fin_promo")
    @NotNull(message = "Promotion end date is required")
    LocalDate dateFinPromo;

    // inverse side of many-to-many with Article
    @ManyToMany(mappedBy = "promotions")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @Builder.Default
    List<Article> articles = new ArrayList<>();
}