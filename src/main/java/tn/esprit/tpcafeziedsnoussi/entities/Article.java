package tn.esprit.tpcafeziedsnoussi.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.tpcafeziedsnoussi.enums.TypeArticle;

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
}