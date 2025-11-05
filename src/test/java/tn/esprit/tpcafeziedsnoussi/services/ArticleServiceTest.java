package tn.esprit.tpcafeziedsnoussi.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpcafeziedsnoussi.entities.Article;
import tn.esprit.tpcafeziedsnoussi.enums.TypeArticle;
import tn.esprit.tpcafeziedsnoussi.exceptions.ResourceNotFoundException;
import tn.esprit.tpcafeziedsnoussi.repositories.ArticleRepository;
import tn.esprit.tpcafeziedsnoussi.services.implementation.ArticleService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;

    private Article testArticle;

    @BeforeEach
    void setUp() {
        testArticle = Article.builder()
                .idArticle(1L)
                .nomArticle("Espresso")
                .prixArticle(2.5f)
                .typeArticle(TypeArticle.BOISSON)
                .build();
    }

    @Test
    void shouldAddArticle() {
        // Given
        when(articleRepository.save(any(Article.class))).thenReturn(testArticle);

        // When
        Article savedArticle = articleService.addArticle(testArticle);

        // Then
        assertThat(savedArticle).isNotNull();
        assertThat(savedArticle.getNomArticle()).isEqualTo("Espresso");
        verify(articleRepository, times(1)).save(testArticle);
    }

    @Test
    void shouldFindArticleById() {
        // Given
        when(articleRepository.findById(1L)).thenReturn(Optional.of(testArticle));

        // When
        Article foundArticle = articleService.selectArticleByIdWithOrElse(1L);

        // Then
        assertThat(foundArticle).isNotNull();
        assertThat(foundArticle.getIdArticle()).isEqualTo(1L);
        verify(articleRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenArticleNotFound() {
        // Given
        when(articleRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> articleService.selectArticleByIdWithOrElse(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Article not found with id: '999'");
    }

    @Test
    void shouldFindAllArticles() {
        // Given
        List<Article> articles = Arrays.asList(testArticle);
        when(articleRepository.findAll()).thenReturn(articles);

        // When
        List<Article> foundArticles = articleService.selectAllArticles();

        // Then
        assertThat(foundArticles).hasSize(1);
        verify(articleRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateArticle() {
        // Given
        when(articleRepository.existsById(1L)).thenReturn(true);
        when(articleRepository.save(any(Article.class))).thenReturn(testArticle);

        // When
        Article updatedArticle = articleService.updateArticle(testArticle);

        // Then
        assertThat(updatedArticle).isNotNull();
        verify(articleRepository, times(1)).existsById(1L);
        verify(articleRepository, times(1)).save(testArticle);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentArticle() {
        // Given
        when(articleRepository.existsById(anyLong())).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> articleService.updateArticle(testArticle))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldPatchArticleById() {
        // Given
        Article patchData = Article.builder()
                .nomArticle("Updated Espresso")
                .prixArticle(3.0f)
                .build();
        
        when(articleRepository.findById(1L)).thenReturn(Optional.of(testArticle));
        when(articleRepository.save(any(Article.class))).thenReturn(testArticle);

        // When
        Article result = articleService.patchArticleById(1L, patchData);

        // Then
        assertThat(result).isNotNull();
        verify(articleRepository, times(1)).findById(1L);
        verify(articleRepository, times(1)).save(any(Article.class));
    }

    @Test
    void shouldDeleteArticleById() {
        // Given
        when(articleRepository.existsById(1L)).thenReturn(true);
        doNothing().when(articleRepository).deleteById(1L);

        // When
        articleService.deleteArticleById(1L);

        // Then
        verify(articleRepository, times(1)).existsById(1L);
        verify(articleRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentArticle() {
        // Given
        when(articleRepository.existsById(anyLong())).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> articleService.deleteArticleById(999L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldVerifyArticleExists() {
        // Given
        when(articleRepository.existsById(1L)).thenReturn(true);
        when(articleRepository.existsById(999L)).thenReturn(false);

        // When
        boolean exists = articleService.verifArticleById(1L);
        boolean notExists = articleService.verifArticleById(999L);

        // Then
        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }
}
