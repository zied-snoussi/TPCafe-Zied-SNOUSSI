package tn.esprit.tpcafeziedsnoussi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tpcafeziedsnoussi.enums.TypeArticle;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article")
    Long idArticle;

    @Column(name = "nom_article")
    String nomArticle;

    @Column(name = "prix_article")
    float prixArticle;

    @Column(name = "type_article")
    @Enumerated(EnumType.STRING)
    TypeArticle typeArticle;

    // One Article can be referenced by many Detail_Commande
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    List<Detail_Commande> detailCommandes = new ArrayList<>();

    // Many-to-many between Article and Promotion
    @ManyToMany
    @JoinTable(
        name = "article_promotion",
        joinColumns = @JoinColumn(name = "id_article"),
        inverseJoinColumns = @JoinColumn(name = "id_promotion")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Promotion> promotions = new ArrayList<>();
}