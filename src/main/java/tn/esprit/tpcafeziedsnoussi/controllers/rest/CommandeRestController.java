package tn.esprit.tpcafeziedsnoussi.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpcafeziedsnoussi.entities.Commande;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.ICommandeService;

import java.util.List;

@RestController
@RequestMapping("/commandes")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Order Management", description = "APIs for managing orders in the TPCafe system")
public class CommandeRestController {

    private final ICommandeService commandeService;

    @PostMapping
    @Operation(summary = "Create a new order", description = "Creates a new order in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully",
                    content = @Content(schema = @Schema(implementation = Commande.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Commande> addCommande(
            @Parameter(description = "Order data to create", required = true)
            @RequestBody Commande commande) {
        return ResponseEntity.ok(commandeService.addCommande(commande));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple orders", description = "Creates multiple orders in a single operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<List<Commande>> addCommandes(
            @Parameter(description = "List of orders to create", required = true)
            @RequestBody List<Commande> commandes) {
        return ResponseEntity.ok(commandeService.saveCommandes(commandes));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieves a specific order by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found",
                    content = @Content(schema = @Schema(implementation = Commande.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    public ResponseEntity<Commande> getCommandeById(
            @Parameter(description = "ID of the order to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(commandeService.selectCommandeByIdWithOrElse(id));
    }

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieves a list of all orders in the system")
    @ApiResponse(responseCode = "200", description = "List of orders retrieved successfully")
    public ResponseEntity<List<Commande>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.selectAllCommandes());
    }

    @PutMapping
    @Operation(summary = "Update an order", description = "Updates an existing order (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated successfully",
                    content = @Content(schema = @Schema(implementation = Commande.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Commande> updateCommande(
            @Parameter(description = "Order data to update", required = true)
            @RequestBody Commande commande) {
        return ResponseEntity.ok(commandeService.updateCommande(commande));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update an order", description = "Updates specific fields of an existing order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order partially updated successfully",
                    content = @Content(schema = @Schema(implementation = Commande.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Commande> patchCommande(
            @Parameter(description = "ID of the order to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial order data to update", required = true)
            @RequestBody Commande commande) {
        return ResponseEntity.ok(commandeService.patchCommandeById(id, commande));
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
}
