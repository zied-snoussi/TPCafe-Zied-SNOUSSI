package tn.esprit.tpcafeziedsnoussi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article")
    Long idArticle;

    @Column(name = "nom_article")
    @NotBlank(message = "Article name is required")
    @Size(min = 2, max = 100, message = "Article name must be between 2 and 100 characters")
    String nomArticle;

    @Column(name = "prix_article")
    @Positive(message = "Article price must be positive")
    float prixArticle;

    @Column(name = "type_article")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Article type is required")
    TypeArticle typeArticle;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @Builder.Default
    List<Detail_Commande> detailCommandes = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "article_promotion",
        joinColumns = @JoinColumn(name = "id_article"),
        inverseJoinColumns = @JoinColumn(name = "id_promotion")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    List<Promotion> promotions = new ArrayList<>();
}