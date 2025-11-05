package tn.esprit.tpcafeziedsnoussi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.repositories.ClientRepository;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class ClientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    void shouldCreateAndRetrieveClient() throws Exception {
        // Create client
        String response = mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Snoussi")))
                .andExpect(jsonPath("$.prenom", is("Zied")))
                .andExpect(jsonPath("$.idClient").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Client createdClient = objectMapper.readValue(response, Client.class);

        // Retrieve client
        mockMvc.perform(get("/clients/" + createdClient.getIdClient())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Snoussi")))
                .andExpect(jsonPath("$.prenom", is("Zied")));
    }

    @Test
    void shouldUpdateClient() throws Exception {
        // Create client
        Client savedClient = clientRepository.save(testClient);

        // Update client
        savedClient.setNom("Updated Name");
        savedClient.setPrenom("Updated Prenom");

        mockMvc.perform(put("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(savedClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Updated Name")))
                .andExpect(jsonPath("$.prenom", is("Updated Prenom")));
    }

    @Test
    void shouldDeleteClient() throws Exception {
        // Create client
        Client savedClient = clientRepository.save(testClient);

        // Delete client
        mockMvc.perform(delete("/clients/" + savedClient.getIdClient())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify deletion
        mockMvc.perform(get("/clients/" + savedClient.getIdClient())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllClients() throws Exception {
        // Create multiple clients
        clientRepository.save(testClient);
        clientRepository.save(Client.builder()
                .nom("Ben Ali")
                .prenom("Ahmed")
                .dateNaissance(LocalDate.of(1985, 5, 20))
                .build());

        // Get all clients
        mockMvc.perform(get("/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldValidateClientInput() throws Exception {
        // Create invalid client
        Client invalidClient = Client.builder()
                .nom("A") // Too short
                .prenom("") // Empty
                .dateNaissance(LocalDate.of(2030, 1, 1)) // Future date
                .build();

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidClient)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.validationErrors", hasSize(greaterThan(0))));
    }

    @Test
    void shouldReturn404ForNonExistentClient() throws Exception {
        mockMvc.perform(get("/clients/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message").value(containsString("Client not found")));
    }

    @Test
    void shouldPatchClient() throws Exception {
        // Create client
        Client savedClient = clientRepository.save(testClient);

        // Patch client
        Client patchData = Client.builder()
                .nom("Patched Name")
                .build();

        mockMvc.perform(patch("/clients/" + savedClient.getIdClient())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patchData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Patched Name")))
                .andExpect(jsonPath("$.prenom", is("Zied"))); // Should remain unchanged
    }
}
