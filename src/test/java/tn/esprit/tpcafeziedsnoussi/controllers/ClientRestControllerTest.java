package tn.esprit.tpcafeziedsnoussi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.tpcafeziedsnoussi.controllers.rest.ClientRestController;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.exceptions.ResourceNotFoundException;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IClientService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientRestController.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClientRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IClientService clientService;

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
    void shouldGetAllClients() throws Exception {
        // Given
        List<Client> clients = Arrays.asList(testClient);
        when(clientService.selectAllClients()).thenReturn(clients);

        // When & Then
        mockMvc.perform(get("/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nom", is("Snoussi")))
                .andExpect(jsonPath("$[0].prenom", is("Zied")));

        verify(clientService, times(1)).selectAllClients();
    }

    @Test
    void shouldGetClientById() throws Exception {
        // Given
        when(clientService.selectClientByIdWithOrElse(1L)).thenReturn(testClient);

        // When & Then
        mockMvc.perform(get("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClient", is(1)))
                .andExpect(jsonPath("$.nom", is("Snoussi")))
                .andExpect(jsonPath("$.prenom", is("Zied")));

        verify(clientService, times(1)).selectClientByIdWithOrElse(1L);
    }

    @Test
    void shouldReturn404WhenClientNotFound() throws Exception {
        // Given
        when(clientService.selectClientByIdWithOrElse(anyLong()))
                .thenThrow(new ResourceNotFoundException("Client", "id", 999L));

        // When & Then
        mockMvc.perform(get("/clients/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void shouldCreateClient() throws Exception {
        // Given
        when(clientService.addClient(any(Client.class))).thenReturn(testClient);

        // When & Then
        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Snoussi")))
                .andExpect(jsonPath("$.prenom", is("Zied")));

        verify(clientService, times(1)).addClient(any(Client.class));
    }

    @Test
    void shouldReturn400WhenCreatingInvalidClient() throws Exception {
        // Given
        Client invalidClient = Client.builder()
                .nom("A") // Too short
                .prenom("") // Empty
                .dateNaissance(LocalDate.of(2030, 1, 1)) // Future date
                .build();

        // When & Then
        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidClient)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.validationErrors").exists());

        verify(clientService, never()).addClient(any(Client.class));
    }

    @Test
    void shouldUpdateClient() throws Exception {
        // Given
        when(clientService.updateClient(any(Client.class))).thenReturn(testClient);

        // When & Then
        mockMvc.perform(put("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Snoussi")));

        verify(clientService, times(1)).updateClient(any(Client.class));
    }

    @Test
    void shouldPatchClient() throws Exception {
        // Given
        Client patchedClient = Client.builder()
                .idClient(1L)
                .nom("Updated Name")
                .prenom("Zied")
                .dateNaissance(LocalDate.of(1990, 1, 15))
                .build();
        
        when(clientService.updateClientById(anyLong(), any(Client.class))).thenReturn(patchedClient);

        // When & Then
        mockMvc.perform(patch("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patchedClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Updated Name")));

        verify(clientService, times(1)).updateClientById(anyLong(), any(Client.class));
    }

    @Test
    void shouldDeleteClientById() throws Exception {
        // Given
        doNothing().when(clientService).deleteClientById(1L);

        // When & Then
        mockMvc.perform(delete("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientService, times(1)).deleteClientById(1L);
    }

    @Test
    void shouldVerifyClientExists() throws Exception {
        // Given
        when(clientService.verifClientById(1L)).thenReturn(true);

        // When & Then
        mockMvc.perform(get("/clients/verif/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(clientService, times(1)).verifClientById(1L);
    }
}
