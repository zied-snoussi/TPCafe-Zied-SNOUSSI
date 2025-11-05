package tn.esprit.tpcafeziedsnoussi.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.tpcafeziedsnoussi.entities.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private Client testClient;

    @BeforeEach
    void setUp() {
        clientRepository.deleteAll();
        
        testClient = Client.builder()
                .nom("Snoussi")
                .prenom("Zied")
                .dateNaissance(LocalDate.of(1990, 1, 15))
                .build();
    }

    @Test
    void shouldSaveClient() {
        // When
        Client savedClient = clientRepository.save(testClient);

        // Then
        assertThat(savedClient).isNotNull();
        assertThat(savedClient.getIdClient()).isNotNull();
        assertThat(savedClient.getNom()).isEqualTo("Snoussi");
        assertThat(savedClient.getPrenom()).isEqualTo("Zied");
    }

    @Test
    void shouldFindClientById() {
        // Given
        Client savedClient = clientRepository.save(testClient);

        // When
        Optional<Client> foundClient = clientRepository.findById(savedClient.getIdClient());

        // Then
        assertThat(foundClient).isPresent();
        assertThat(foundClient.get().getNom()).isEqualTo("Snoussi");
    }

    @Test
    void shouldFindAllClients() {
        // Given
        clientRepository.save(testClient);
        clientRepository.save(Client.builder()
                .nom("Ben Ali")
                .prenom("Ahmed")
                .dateNaissance(LocalDate.of(1985, 5, 20))
                .build());

        // When
        List<Client> clients = clientRepository.findAll();

        // Then
        assertThat(clients).hasSize(2);
    }

    @Test
    void shouldUpdateClient() {
        // Given
        Client savedClient = clientRepository.save(testClient);

        // When
        savedClient.setNom("Updated Name");
        Client updatedClient = clientRepository.save(savedClient);

        // Then
        assertThat(updatedClient.getNom()).isEqualTo("Updated Name");
    }

    @Test
    void shouldDeleteClient() {
        // Given
        Client savedClient = clientRepository.save(testClient);
        Long clientId = savedClient.getIdClient();

        // When
        clientRepository.deleteById(clientId);

        // Then
        Optional<Client> deletedClient = clientRepository.findById(clientId);
        assertThat(deletedClient).isEmpty();
    }

    @Test
    void shouldReturnEmptyWhenClientNotFound() {
        // When
        Optional<Client> foundClient = clientRepository.findById(999L);

        // Then
        assertThat(foundClient).isEmpty();
    }

    @Test
    void shouldCheckIfClientExists() {
        // Given
        Client savedClient = clientRepository.save(testClient);

        // When
        boolean exists = clientRepository.existsById(savedClient.getIdClient());
        boolean notExists = clientRepository.existsById(999L);

        // Then
        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }
}
