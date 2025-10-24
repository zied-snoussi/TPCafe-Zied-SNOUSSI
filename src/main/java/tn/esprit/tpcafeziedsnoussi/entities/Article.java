package tn.esprit.tpcafeziedsnoussi.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.tpcafeziedsnoussi.enums.TypeArticle;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article")
    private Long idArticle;

    @Column(name = "nom_article")
    private String nomArticle;

    @Column(name = "prix_article")
    private float prixArticle;

    @Column(name = "type_article")
    @Enumerated(EnumType.STRING)
    private TypeArticle typeArticle;

    // One Article can be referenced by many Detail_Commande
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Detail_Commande> detailCommandes = new ArrayList<>();

    // Many-to-many between Article and Promotion
    @ManyToMany
    @JoinTable(
            name = "article_promotion",
            joinColumns = @JoinColumn(name = "id_article"),
            inverseJoinColumns = @JoinColumn(name = "id_promotion")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Promotion> promotions = new ArrayList<>();
}