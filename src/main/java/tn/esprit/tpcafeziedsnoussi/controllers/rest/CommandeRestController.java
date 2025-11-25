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
import tn.esprit.tpcafeziedsnoussi.dtos.CommandeDTO;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.entities.Commande;
import tn.esprit.tpcafeziedsnoussi.exceptions.ResourceNotFoundException;
import tn.esprit.tpcafeziedsnoussi.mappers.CommandeMapper;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.ICommandeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/commandes")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Order Management", description = "APIs for managing orders in the TPCafe system")
public class CommandeRestController {

    private final ICommandeService commandeService;
    private final CommandeMapper commandeMapper;

    @PostMapping
    @Operation(summary = "Create a new order", description = "Creates a new order in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully",
                    content = @Content(schema = @Schema(implementation = CommandeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<CommandeDTO> addCommande(
            @Parameter(description = "Order data to create", required = true)
            @Valid @RequestBody CommandeDTO commandeDTO) {
        var commande = commandeMapper.toEntity(commandeDTO);
        var savedCommande = commandeService.addCommande(commande);
        return ResponseEntity.ok(commandeMapper.toDTO(savedCommande));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple orders", description = "Creates multiple orders in a single operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<List<CommandeDTO>> addCommandes(
            @Parameter(description = "List of orders to create", required = true)
            @RequestBody List<CommandeDTO> commandeDTOs) {
        var commandes = commandeDTOs.stream()
                .map(commandeMapper::toEntity)
                .collect(Collectors.toList());
        var savedCommandes = commandeService.saveCommandes(commandes);
        return ResponseEntity.ok(savedCommandes.stream()
                .map(commandeMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieves a specific order by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found",
                    content = @Content(schema = @Schema(implementation = CommandeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    public ResponseEntity<CommandeDTO> getCommandeById(
            @Parameter(description = "ID of the order to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(commandeMapper.toDTO(commandeService.selectCommandeByIdWithOrElse(id)));
    }

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieves a list of all orders in the system")
    @ApiResponse(responseCode = "200", description = "List of orders retrieved successfully")
    public ResponseEntity<List<CommandeDTO>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.selectAllCommandes().stream()
                .map(commandeMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping
    @Operation(summary = "Update an order", description = "Updates an existing order (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated successfully",
                    content = @Content(schema = @Schema(implementation = CommandeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<CommandeDTO> updateCommande(
            @Parameter(description = "Order data to update", required = true)
            @Valid @RequestBody CommandeDTO commandeDTO) {
        var commande = commandeMapper.toEntity(commandeDTO);
        var updatedCommande = commandeService.updateCommande(commande);
        return ResponseEntity.ok(commandeMapper.toDTO(updatedCommande));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update an order", description = "Updates specific fields of an existing order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order partially updated successfully",
                    content = @Content(schema = @Schema(implementation = CommandeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<CommandeDTO> patchCommande(
            @Parameter(description = "ID of the order to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial order data to update", required = true)
            @RequestBody CommandeDTO commandeDTO) {
        var commande = commandeMapper.toEntity(commandeDTO);
        var patchedCommande = commandeService.patchCommandeById(id, commande);
        return ResponseEntity.ok(commandeMapper.toDTO(patchedCommande));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order by ID", description = "Deletes a specific order by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    public ResponseEntity<Void> deleteCommandeById(
            @Parameter(description = "ID of the order to delete", required = true)
            @PathVariable Long id) {
        commandeService.deleteCommandeById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete all orders", description = "Deletes all orders from the system")
    @ApiResponse(responseCode = "200", description = "All orders deleted successfully")
    public ResponseEntity<Void> deleteAllCommandes() {
        commandeService.deleteAllCommandes();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idCommande}/client/{idClient}")
    @Operation(summary = "Assign order to client", description = "Assigns an order to a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order assigned to client successfully"),
            @ApiResponse(responseCode = "404", description = "Order or Client not found", content = @Content)
    })
    public ResponseEntity<Void> affecterCommandeToClient(
            @Parameter(description = "ID of the order", required = true) @PathVariable Long idCommande,
            @Parameter(description = "ID of the client", required = true) @PathVariable Long idClient) {
        commandeService.affecterCommandeToClient(idCommande, idClient);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idCommande}/client")
    @Operation(summary = "Unassign client from order", description = "Removes the client assignment from an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client unassigned from order successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    public ResponseEntity<Void> desaffecterClientdeCommande(
            @Parameter(description = "ID of the order", required = true) @PathVariable Long idCommande) {
        commandeService.desaffecterClientdeCommande(idCommande);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ajouterAClient")
    @Operation(summary = "Add order and assign to client", description = "Creates a new order and assigns it to a client based on their name and surname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created and assigned to client successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<CommandeDTO> ajouterCommandeEtAffecterAClient(
            @Parameter(description = "Order data to create", required = true)
            @Valid @RequestBody CommandeDTO commandeDTO,
            @Parameter(description = "Client's first name", required = true)
            @RequestParam String nomClient,
            @Parameter(description = "Client's last name", required = true)
            @RequestParam String prenomClient) {
        var commande = commandeMapper.toEntity(commandeDTO);
        commandeService.ajouterCommandeEtAffecterAClient(commande, nomClient, prenomClient);
        return ResponseEntity.ok(commandeMapper.toDTO(commande));
    }
}
