package tn.esprit.tpcafeziedsnoussi.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
@CrossOrigin("*")
@Tag(name = "Client Management", description = "APIs for managing clients/customers in the TPCafe system")
public class ClientRestController {
    IClientService clientService;

    @GetMapping
    @Operation(summary = "Get all clients", description = "Retrieves a list of all clients in the system")
    @ApiResponse(responseCode = "200", description = "List of clients retrieved successfully")
    public ResponseEntity<List<Client>> selectAllClients() {
        return ResponseEntity.ok(clientService.selectAllClients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID", description = "Retrieves a specific client by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found",
                    content = @Content(schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    public ResponseEntity<Client> selectClientByIdWithOrElse(
            @Parameter(description = "ID of the client to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(clientService.selectClientByIdWithOrElse(id));
    }

    @PostMapping
    @Operation(summary = "Create a new client", description = "Creates a new client in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created successfully",
                    content = @Content(schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Client> addClient(
            @Parameter(description = "Client data to create", required = true)
            @RequestBody Client client) {
        return ResponseEntity.ok(clientService.addClient(client));
    }

    @PutMapping
    @Operation(summary = "Update a client", description = "Updates an existing client (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully",
                    content = @Content(schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Client> updateClient(
            @Parameter(description = "Client data to update", required = true)
            @RequestBody Client client) {
        return ResponseEntity.ok(clientService.updateClient(client));
    }

    @DeleteMapping
    @Operation(summary = "Delete a client", description = "Deletes a client from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    public ResponseEntity<Void> deleteClient(
            @Parameter(description = "Client to delete", required = true)
            @RequestBody Client client) {
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
                    content = @Content(schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Client> updateClientById(
            @Parameter(description = "ID of the client to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial client data to update", required = true)
            @RequestBody Client client) {
        return ResponseEntity.ok(clientService.updateClientById(id, client));
    }
}
