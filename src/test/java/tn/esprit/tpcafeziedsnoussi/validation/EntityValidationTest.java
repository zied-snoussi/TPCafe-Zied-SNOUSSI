package tn.esprit.tpcafeziedsnoussi.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tn.esprit.tpcafeziedsnoussi.entities.Adresse;
import tn.esprit.tpcafeziedsnoussi.entities.Article;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.entities.Promotion;
import tn.esprit.tpcafeziedsnoussi.enums.TypeArticle;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for entity validation annotations
 */
class EntityValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldValidateValidClient() {
        // Given
        Client client = Client.builder()
                .nom("Snoussi")
                .prenom("Zied")
                .dateNaissance(LocalDate.of(1990, 1, 15))
                .build();

        // When
        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailWhenClientNameTooShort() {
        // Given
        Client client = Client.builder()
                .nom("A")
                .prenom("Zied")
                .dateNaissance(LocalDate.of(1990, 1, 15))
                .build();

        // When
        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .contains("Last name must be between 2 and 50 characters");
    }

    @Test
    void shouldFailWhenClientNameIsBlank() {
        // Given
        Client client = Client.builder()
                .nom("")
                .prenom("Zied")
                .dateNaissance(LocalDate.of(1990, 1, 15))
                .build();

        // When
        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        // Then
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(v -> v.getMessage().contains("Last name is required")))
                .isTrue();
    }

    @Test
    void shouldFailWhenClientBirthDateInFuture() {
        // Given
        Client client = Client.builder()
                .nom("Snoussi")
                .prenom("Zied")
                .dateNaissance(LocalDate.now().plusDays(1))
                .build();

        // When
        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .contains("Birth date must be in the past");
    }

    @Test
    void shouldValidateValidArticle() {
        // Given
        Article article = Article.builder()
                .nomArticle("Espresso")
                .prixArticle(2.5f)
                .typeArticle(TypeArticle.BOISSON)
                .build();

        // When
        Set<ConstraintViolation<Article>> violations = validator.validate(article);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailWhenArticlePriceIsNegative() {
        // Given
        Article article = Article.builder()
                .nomArticle("Espresso")
                .prixArticle(-2.5f)
                .typeArticle(TypeArticle.BOISSON)
                .build();

        // When
        Set<ConstraintViolation<Article>> violations = validator.validate(article);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .contains("Article price must be positive");
    }

    @Test
    void shouldFailWhenArticleTypeIsNull() {
        // Given
        Article article = Article.builder()
                .nomArticle("Espresso")
                .prixArticle(2.5f)
                .typeArticle(null)
                .build();

        // When
        Set<ConstraintViolation<Article>> violations = validator.validate(article);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .contains("Article type is required");
    }

    @Test
    void shouldValidateValidAdresse() {
        // Given
        Adresse adresse = Adresse.builder()
                .rue("123 Avenue Habib Bourguiba")
                .ville("Tunis")
                .codePostal("1000")
                .build();

        // When
        Set<ConstraintViolation<Adresse>> violations = validator.validate(adresse);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailWhenPostalCodeInvalid() {
        // Given
        Adresse adresse = Adresse.builder()
                .rue("123 Avenue Habib Bourguiba")
                .ville("Tunis")
                .codePostal("ABC")
                .build();

        // When
        Set<ConstraintViolation<Adresse>> violations = validator.validate(adresse);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .contains("Postal code must be 4 digits");
    }

    @Test
    void shouldValidateValidPromotion() {
        // Given
        Promotion promotion = Promotion.builder()
                .pourcentagePromo(20.0f)
                .dateDebutPromo(LocalDate.now())
                .dateFinPromo(LocalDate.now().plusDays(30))
                .build();

        // When
        Set<ConstraintViolation<Promotion>> violations = validator.validate(promotion);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailWhenPromotionPercentageExceeds100() {
        // Given
        Promotion promotion = Promotion.builder()
                .pourcentagePromo(150.0f)
                .dateDebutPromo(LocalDate.now())
                .dateFinPromo(LocalDate.now().plusDays(30))
                .build();

        // When
        Set<ConstraintViolation<Promotion>> violations = validator.validate(promotion);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .contains("Promotion percentage cannot exceed 100");
    }

    @Test
    void shouldFailWhenPromotionPercentageIsNegative() {
        // Given
        Promotion promotion = Promotion.builder()
                .pourcentagePromo(-10.0f)
                .dateDebutPromo(LocalDate.now())
                .dateFinPromo(LocalDate.now().plusDays(30))
                .build();

        // When
        Set<ConstraintViolation<Promotion>> violations = validator.validate(promotion);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .contains("Promotion percentage must be at least 0");
    }
}
