package tn.esprit.tpcafeziedsnoussi.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detail_commande")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Detail_Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_commande")
    private Long idDetailCommande;

    @Column(name = "quantite_article")
    private int quantiteArticle;

    @Column(name = "sous_total_detail_article")
    private float sousTotalDetailArticle;

    @Column(name = "sous_total_detail_article_apres_promo")
    private float sousTotalDetailArticleApresPromo;
}