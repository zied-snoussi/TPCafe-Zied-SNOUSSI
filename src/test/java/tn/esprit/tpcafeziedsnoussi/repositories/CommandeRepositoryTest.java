package tn.esprit.tpcafeziedsnoussi.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.entities.Commande;
import tn.esprit.tpcafeziedsnoussi.enums.StatusCommande;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CommandeRepositoryTest {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ClientRepository clientRepository;

    private Commande testCommande;
    private Client testClient;

    @BeforeEach
    void setUp() {
        commandeRepository.deleteAll();
        clientRepository.deleteAll();
        
        testClient = Client.builder()
                .nom("Snoussi")
                .prenom("Zied")
                .dateNaissance(LocalDate.of(1990, 1, 15))
                .build();
        testClient = clientRepository.save(testClient);
        
        testCommande = Commande.builder()
                .dateCommande(LocalDate.now())
                .totalCommande(15.5f)
                .statusCommande(StatusCommande.EN_COURS)
                .client(testClient)
                .build();
    }

    @Test
    void shouldSaveCommande() {
        // When
        Commande savedCommande = commandeRepository.save(testCommande);

        // Then
        assertThat(savedCommande).isNotNull();
        assertThat(savedCommande.getIdCommande()).isNotNull();
        assertThat(savedCommande.getTotalCommande()).isEqualTo(15.5f);
        assertThat(savedCommande.getStatusCommande()).isEqualTo(StatusCommande.EN_COURS);
    }

    @Test
    void shouldFindCommandeById() {
        // Given
        Commande savedCommande = commandeRepository.save(testCommande);

        // When
        Optional<Commande> foundCommande = commandeRepository.findById(savedCommande.getIdCommande());

        // Then
        assertThat(foundCommande).isPresent();
        assertThat(foundCommande.get().getTotalCommande()).isEqualTo(15.5f);
    }

    @Test
    void shouldFindAllCommandes() {
        // Given
        commandeRepository.save(testCommande);
        commandeRepository.save(Commande.builder()
                .dateCommande(LocalDate.now())
                .totalCommande(25.0f)
                .statusCommande(StatusCommande.LIVREE)
                .client(testClient)
                .build());

        // When
        List<Commande> commandes = commandeRepository.findAll();

        // Then
        assertThat(commandes).hasSize(2);
    }

    @Test
    void shouldUpdateCommande() {
        // Given
        Commande savedCommande = commandeRepository.save(testCommande);

        // When
        savedCommande.setStatusCommande(StatusCommande.LIVREE);
        savedCommande.setTotalCommande(20.0f);
        Commande updatedCommande = commandeRepository.save(savedCommande);

        // Then
        assertThat(updatedCommande.getStatusCommande()).isEqualTo(StatusCommande.LIVREE);
        assertThat(updatedCommande.getTotalCommande()).isEqualTo(20.0f);
    }

    @Test
    void shouldDeleteCommande() {
        // Given
        Commande savedCommande = commandeRepository.save(testCommande);
        Long commandeId = savedCommande.getIdCommande();

        // When
        commandeRepository.deleteById(commandeId);

        // Then
        Optional<Commande> deletedCommande = commandeRepository.findById(commandeId);
        assertThat(deletedCommande).isEmpty();
    }

    @Test
    void shouldMaintainRelationshipWithClient() {
        // Given
        Commande savedCommande = commandeRepository.save(testCommande);

        // When
        Optional<Commande> foundCommande = commandeRepository.findById(savedCommande.getIdCommande());

        // Then
        assertThat(foundCommande).isPresent();
        assertThat(foundCommande.get().getClient()).isNotNull();
        assertThat(foundCommande.get().getClient().getNom()).isEqualTo("Snoussi");
    }
}
