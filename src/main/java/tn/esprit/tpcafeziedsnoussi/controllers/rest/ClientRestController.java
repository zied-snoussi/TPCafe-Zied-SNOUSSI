package tn.esprit.tpcafeziedsnoussi.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpcafeziedsnoussi.dtos.ClientDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Adresse;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.mappers.ClientMapper;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IClientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
@CrossOrigin("*")
@Tag(name = "Client Management", description = "APIs for managing clients/customers in the TPCafe system")
public class ClientRestController {
    private final IClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    @Operation(summary = "Get all clients", description = "Retrieves a list of all clients in the system")
    @ApiResponse(responseCode = "200", description = "List of clients retrieved successfully")
    public ResponseEntity<List<ClientDTO>> selectAllClients() {
        return ResponseEntity.ok(clientService.selectAllClients().stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID", description = "Retrieves a specific client by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    public ResponseEntity<ClientDTO> selectClientByIdWithOrElse(
            @Parameter(description = "ID of the client to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(clientMapper.toDTO(clientService.selectClientByIdWithOrElse(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new client", description = "Creates a new client in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created successfully",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<ClientDTO> addClient(
            @Parameter(description = "Client data to create", required = true)
            @Valid @RequestBody ClientDTO clientDTO) {
        var client = clientMapper.toEntity(clientDTO);
        var savedClient = clientService.addClient(client);
        return ResponseEntity.ok(clientMapper.toDTO(savedClient));
    }

    @PutMapping
    @Operation(summary = "Update a client", description = "Updates an existing client (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<ClientDTO> updateClient(
            @Parameter(description = "Client data to update", required = true)
            @Valid @RequestBody ClientDTO clientDTO) {
        var client = clientMapper.toEntity(clientDTO);
        var updatedClient = clientService.updateClient(client);
        return ResponseEntity.ok(clientMapper.toDTO(updatedClient));
    }

    @DeleteMapping
    @Operation(summary = "Delete a client", description = "Deletes a client from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    public ResponseEntity<Void> deleteClient(
            @Parameter(description = "Client to delete", required = true)
            @RequestBody ClientDTO clientDTO) {
        var client = clientMapper.toEntity(clientDTO);
        clientService.deleteClient(client);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete client by ID", description = "Deletes a specific client by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    public ResponseEntity<Void> deleteClientById(
            @Parameter(description = "ID of the client to delete", required = true)
            @PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verif/{id}")
    @Operation(summary = "Verify client exists", description = "Checks if a client exists by their ID")
    @ApiResponse(responseCode = "200", description = "Verification result returned")
    public ResponseEntity<Boolean> verifClientById(
            @Parameter(description = "ID of the client to verify", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(clientService.verifClientById(id));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update a client", description = "Updates specific fields of an existing client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client partially updated successfully",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<ClientDTO> updateClientById(
            @Parameter(description = "ID of the client to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial client data to update", required = true)
            @RequestBody ClientDTO clientDTO) {
        var client = clientMapper.toEntity(clientDTO);
        var updatedClient = clientService.updateClientById(id, client);
        return ResponseEntity.ok(clientMapper.toDTO(updatedClient));
    }

    @PutMapping("/{idClient}/adresse/{idAdresse}")
    @Operation(summary = "Assign address to client", description = "Assigns an existing address to a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address assigned successfully"),
            @ApiResponse(responseCode = "404", description = "Client or Address not found", content = @Content)
    })
    public ResponseEntity<String> affecterAdresseAClient(
            @Parameter(description = "ID of the client", required = true) @PathVariable Long idClient,
            @Parameter(description = "ID of the address", required = true) @PathVariable Long idAdresse) {
        return ResponseEntity.ok(clientService.affecterAdresseAClient(idAdresse, idClient));
    }

    @PutMapping("/{idClient}/carte/{idCarte}")
    @Operation(summary = "Assign loyalty card to client", description = "Assigns an existing loyalty card to a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loyalty card assigned successfully"),
            @ApiResponse(responseCode = "404", description = "Client or Card not found", content = @Content)
    })
    public ResponseEntity<Void> affecterCarteAClient(
            @Parameter(description = "ID of the client", required = true) @PathVariable Long idClient,
            @Parameter(description = "ID of the card", required = true) @PathVariable Long idCarte) {
        clientService.affecterCarteAClient(idCarte, idClient);
        return ResponseEntity.ok().build();
    }

}
