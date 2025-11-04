package tn.esprit.tpcafeziedsnoussi.entities;

import jakarta.persistence.*;
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
//@RequiredArgsConstructor
public class Detail_Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_commande")
    Long idDetailCommande;

    @Column(name = "quantite_article")
    int quantiteArticle;

    @Column(name = "sous_total_detail_article")
    float sousTotalDetailArticle;

    @Column(name = "sous_total_detail_article_apres_promo")
    float sousTotalDetailArticleApresPromo;

    // Many Detail_Commande belong to one Commande
    @ManyToOne
    @JoinColumn(name = "id_commande", referencedColumnName = "id_commande")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Commande commande;

    // Many Detail_Commande refer to one Article
    @ManyToOne
    @JoinColumn(name = "id_article", referencedColumnName = "id_article")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Article article;
}