package tn.esprit.tpcafeziedsnoussi.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.tpcafeziedsnoussi.controllers.rest.ClientRestController;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IClientService;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientRestController.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IClientService clientService;

    @Test
    void shouldHandleResourceNotFoundException() throws Exception {
        // Given
        when(clientService.selectClientByIdWithOrElse(anyLong()))
                .thenThrow(new ResourceNotFoundException("Client", "id", 999L));

        // When & Then
        mockMvc.perform(get("/clients/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message").value("Client not found with id: '999'"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.path").exists());
    }

    @Test
    void shouldHandleBadRequestException() throws Exception {
        // Given
        when(clientService.selectClientByIdWithOrElse(anyLong()))
                .thenThrow(new BadRequestException("Invalid request data"));

        // When & Then
        mockMvc.perform(get("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.message", is("Invalid request data")));
    }

    @Test
    void shouldHandleDuplicateResourceException() throws Exception {
        // Given
        when(clientService.selectClientByIdWithOrElse(anyLong()))
                .thenThrow(new DuplicateResourceException("Client", "email", "test@test.com"));

        // When & Then
        mockMvc.perform(get("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status", is(409)))
                .andExpect(jsonPath("$.error", is("Conflict")))
                .andExpect(jsonPath("$.message").value("Client already exists with email: 'test@test.com'"));
    }

    @Test
    void shouldHandleBusinessException() throws Exception {
        // Given
        when(clientService.selectClientByIdWithOrElse(anyLong()))
                .thenThrow(new BusinessException("Business rule violated"));

        // When & Then
        mockMvc.perform(get("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.status", is(422)))
                .andExpect(jsonPath("$.error", is("Unprocessable Entity")))
                .andExpect(jsonPath("$.message", is("Business rule violated")));
    }

    @Test
    void shouldHandleGenericException() throws Exception {
        // Given
        when(clientService.selectClientByIdWithOrElse(anyLong()))
                .thenThrow(new RuntimeException("Unexpected error"));

        // When & Then
        mockMvc.perform(get("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status", is(500)))
                .andExpect(jsonPath("$.error", is("Internal Server Error")))
                .andExpect(jsonPath("$.message").value("An unexpected error occurred. Please contact support if the problem persists."));
    }
}
