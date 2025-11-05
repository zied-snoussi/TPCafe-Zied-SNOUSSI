package tn.esprit.tpcafeziedsnoussi.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.tpcafeziedsnoussi.entities.Article;
import tn.esprit.tpcafeziedsnoussi.enums.TypeArticle;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    private Article testArticle;

    @BeforeEach
    void setUp() {
        articleRepository.deleteAll();
        
        testArticle = Article.builder()
                .nomArticle("Espresso")
                .prixArticle(2.5f)
                .typeArticle(TypeArticle.BOISSON)
                .build();
    }

    @Test
    void shouldSaveArticle() {
        // When
        Article savedArticle = articleRepository.save(testArticle);

        // Then
        assertThat(savedArticle).isNotNull();
        assertThat(savedArticle.getIdArticle()).isNotNull();
        assertThat(savedArticle.getNomArticle()).isEqualTo("Espresso");
        assertThat(savedArticle.getPrixArticle()).isEqualTo(2.5f);
        assertThat(savedArticle.getTypeArticle()).isEqualTo(TypeArticle.BOISSON);
    }

    @Test
    void shouldFindArticleById() {
        // Given
        Article savedArticle = articleRepository.save(testArticle);

        // When
        Optional<Article> foundArticle = articleRepository.findById(savedArticle.getIdArticle());

        // Then
        assertThat(foundArticle).isPresent();
        assertThat(foundArticle.get().getNomArticle()).isEqualTo("Espresso");
    }

    @Test
    void shouldFindAllArticles() {
        // Given
        articleRepository.save(testArticle);
        articleRepository.save(Article.builder()
                .nomArticle("Croissant")
                .prixArticle(1.5f)
                .typeArticle(TypeArticle.DESSERT)
                .build());

        // When
        List<Article> articles = articleRepository.findAll();

        // Then
        assertThat(articles).hasSize(2);
    }

    @Test
    void shouldUpdateArticle() {
        // Given
        Article savedArticle = articleRepository.save(testArticle);

        // When
        savedArticle.setPrixArticle(3.0f);
        Article updatedArticle = articleRepository.save(savedArticle);

        // Then
        assertThat(updatedArticle.getPrixArticle()).isEqualTo(3.0f);
    }

    @Test
    void shouldDeleteArticle() {
        // Given
        Article savedArticle = articleRepository.save(testArticle);
        Long articleId = savedArticle.getIdArticle();

        // When
        articleRepository.deleteById(articleId);

        // Then
        Optional<Article> deletedArticle = articleRepository.findById(articleId);
        assertThat(deletedArticle).isEmpty();
    }
}
