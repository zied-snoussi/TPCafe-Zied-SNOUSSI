package tn.esprit.tpcafeziedsnoussi.utils;

import tn.esprit.tpcafeziedsnoussi.entities.*;
import tn.esprit.tpcafeziedsnoussi.enums.StatusCommande;
import tn.esprit.tpcafeziedsnoussi.enums.TypeArticle;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Utility class for building test data objects
 */
public class TestDataBuilder {

    public static Client createTestClient() {
        return Client.builder()
                .nom("Snoussi")
                .prenom("Zied")
                .dateNaissance(LocalDate.of(1990, 1, 15))
                .commandes(new ArrayList<>())
                .build();
    }

    public static Client createTestClient(String nom, String prenom) {
        return Client.builder()
                .nom(nom)
                .prenom(prenom)
                .dateNaissance(LocalDate.of(1990, 1, 15))
                .commandes(new ArrayList<>())
                .build();
    }

    public static Article createTestArticle() {
        return Article.builder()
                .nomArticle("Espresso")
                .prixArticle(2.5f)
                .typeArticle(TypeArticle.BOISSON)
                .detailCommandes(new ArrayList<>())
                .promotions(new ArrayList<>())
                .build();
    }

    public static Article createTestArticle(String nom, float prix, TypeArticle type) {
        return Article.builder()
                .nomArticle(nom)
                .prixArticle(prix)
                .typeArticle(type)
                .detailCommandes(new ArrayList<>())
                .promotions(new ArrayList<>())
                .build();
    }

    public static Adresse createTestAdresse() {
        return Adresse.builder()
                .rue("123 Avenue Habib Bourguiba")
                .ville("Tunis")
                .codePostal("1000")
                .build();
    }

    public static Adresse createTestAdresse(String rue, String ville, String codePostal) {
        return Adresse.builder()
                .rue(rue)
                .ville(ville)
                .codePostal(codePostal)
                .build();
    }

    public static CarteFidelite createTestCarteFidelite() {
        return CarteFidelite.builder()
                .pointsAccumules(100)
                .dateCreation(LocalDate.now())
                .build();
    }

    public static CarteFidelite createTestCarteFidelite(int points) {
        return CarteFidelite.builder()
                .pointsAccumules(points)
                .dateCreation(LocalDate.now())
                .build();
    }

    public static Commande createTestCommande() {
        return Commande.builder()
                .dateCommande(LocalDate.now())
                .totalCommande(15.5f)
                .statusCommande(StatusCommande.EN_COURS)
                .detailCommandes(new ArrayList<>())
                .build();
    }

    public static Commande createTestCommande(float total, StatusCommande status) {
        return Commande.builder()
                .dateCommande(LocalDate.now())
                .totalCommande(total)
                .statusCommande(status)
                .detailCommandes(new ArrayList<>())
                .build();
    }

    public static Promotion createTestPromotion() {
        return Promotion.builder()
                .pourcentagePromo(20.0f)
                .dateDebutPromo(LocalDate.now())
                .dateFinPromo(LocalDate.now().plusDays(30))
                .articles(new ArrayList<>())
                .build();
    }

    public static Promotion createTestPromotion(float percentage, LocalDate debut, LocalDate fin) {
        return Promotion.builder()
                .pourcentagePromo(percentage)
                .dateDebutPromo(debut)
                .dateFinPromo(fin)
                .articles(new ArrayList<>())
                .build();
    }

    public static Detail_Commande createTestDetailCommande() {
        return Detail_Commande.builder()
                .quantiteArticle(2)
                .sousTotalDetailArticle(5.0f)
                .sousTotalDetailArticleApresPromo(4.0f)
                .build();
    }

    public static Detail_Commande createTestDetailCommande(int quantite, float sousTotal) {
        return Detail_Commande.builder()
                .quantiteArticle(quantite)
                .sousTotalDetailArticle(sousTotal)
                .sousTotalDetailArticleApresPromo(sousTotal)
                .build();
    }
}
