package tn.esprit.tpcafeziedsnoussi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "detail_commande")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Detail_Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_commande")
    Long idDetailCommande;

    @Column(name = "quantite_article")
    @Positive(message = "Article quantity must be positive")
    int quantiteArticle;

    @Column(name = "sous_total_detail_article")
    @PositiveOrZero(message = "Subtotal must be zero or positive")
    float sousTotalDetailArticle;

    @Column(name = "sous_total_detail_article_apres_promo")
    @PositiveOrZero(message = "Subtotal after promotion must be zero or positive")
    float sousTotalDetailArticleApresPromo;

    @ManyToOne
    @JoinColumn(name = "id_commande", referencedColumnName = "id_commande")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotNull(message = "Order is required")
    Commande commande;

    @ManyToOne
    @JoinColumn(name = "id_article", referencedColumnName = "id_article")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotNull(message = "Article is required")
    Article article;
}