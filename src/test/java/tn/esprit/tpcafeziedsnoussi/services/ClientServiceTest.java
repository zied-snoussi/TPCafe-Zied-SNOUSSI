package tn.esprit.tpcafeziedsnoussi.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.exceptions.ResourceNotFoundException;
import tn.esprit.tpcafeziedsnoussi.repositories.ClientRepository;
import tn.esprit.tpcafeziedsnoussi.services.implementation.ClientService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client testClient;

    @BeforeEach
    void setUp() {
        testClient = Client.builder()
                .idClient(1L)
                .nom("Snoussi")
                .prenom("Zied")
                .dateNaissance(LocalDate.of(1990, 1, 15))
                .build();
    }

    @Test
    void shouldAddClient() {
        // Given
        when(clientRepository.save(any(Client.class))).thenReturn(testClient);

        // When
        Client savedClient = clientService.addClient(testClient);

        // Then
        assertThat(savedClient).isNotNull();
        assertThat(savedClient.getNom()).isEqualTo("Snoussi");
        verify(clientRepository, times(1)).save(testClient);
    }

    @Test
    void shouldSaveMultipleClients() {
        // Given
        List<Client> clients = Arrays.asList(testClient, 
                Client.builder()
                        .nom("Ben Ali")
                        .prenom("Ahmed")
                        .dateNaissance(LocalDate.of(1985, 5, 20))
                        .build());
        when(clientRepository.saveAll(anyList())).thenReturn(clients);

        // When
        List<Client> savedClients = clientService.saveClients(clients);

        // Then
        assertThat(savedClients).hasSize(2);
        verify(clientRepository, times(1)).saveAll(clients);
    }

    @Test
    void shouldFindClientById() {
        // Given
        when(clientRepository.findById(1L)).thenReturn(Optional.of(testClient));

        // When
        Client foundClient = clientService.selectClientByIdWithOrElse(1L);

        // Then
        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getIdClient()).isEqualTo(1L);
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenClientNotFound() {
        // Given
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> clientService.selectClientByIdWithOrElse(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Client not found with id: '999'");
    }

    @Test
    void shouldFindAllClients() {
        // Given
        List<Client> clients = Arrays.asList(testClient);
        when(clientRepository.findAll()).thenReturn(clients);

        // When
        List<Client> foundClients = clientService.selectAllClients();

        // Then
        assertThat(foundClients).hasSize(1);
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateClient() {
        // Given
        when(clientRepository.existsById(1L)).thenReturn(true);
        when(clientRepository.save(any(Client.class))).thenReturn(testClient);

        // When
        Client updatedClient = clientService.updateClient(testClient);

        // Then
        assertThat(updatedClient).isNotNull();
        verify(clientRepository, times(1)).existsById(1L);
        verify(clientRepository, times(1)).save(testClient);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentClient() {
        // Given
        when(clientRepository.existsById(anyLong())).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> clientService.updateClient(testClient))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldUpdateClientById() {
        // Given
        Client updatedData = Client.builder()
                .nom("Updated Name")
                .prenom("Updated Prenom")
                .dateNaissance(LocalDate.of(1992, 3, 10))
                .build();
        
        when(clientRepository.findById(1L)).thenReturn(Optional.of(testClient));
        when(clientRepository.save(any(Client.class))).thenReturn(testClient);

        // When
        Client result = clientService.updateClientById(1L, updatedData);

        // Then
        assertThat(result).isNotNull();
        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void shouldDeleteClientById() {
        // Given
        when(clientRepository.existsById(1L)).thenReturn(true);
        doNothing().when(clientRepository).deleteById(1L);

        // When
        clientService.deleteClientById(1L);

        // Then
        verify(clientRepository, times(1)).existsById(1L);
        verify(clientRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentClient() {
        // Given
        when(clientRepository.existsById(anyLong())).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> clientService.deleteClientById(999L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldVerifyClientExists() {
        // Given
        when(clientRepository.existsById(1L)).thenReturn(true);
        when(clientRepository.existsById(999L)).thenReturn(false);

        // When
        boolean exists = clientService.verifClientById(1L);
        boolean notExists = clientService.verifClientById(999L);

        // Then
        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }

    @Test
    void shouldDeleteAllClients() {
        // Given
        doNothing().when(clientRepository).deleteAll();

        // When
        clientService.deleteAllClients();

        // Then
        verify(clientRepository, times(1)).deleteAll();
    }
}
