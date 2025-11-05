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
import tn.esprit.tpcafeziedsnoussi.entities.Detail_Commande;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IDetailCommandeService;

import java.util.List;

@RestController
@RequestMapping("/detail-commandes")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Order Detail Management", description = "APIs for managing order details/line items in the TPCafe system")
public class DetailCommandeRestController {

    private final IDetailCommandeService detailCommandeService;

    @PostMapping
    @Operation(summary = "Create a new order detail", description = "Creates a new order detail/line item in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order detail created successfully",
                    content = @Content(schema = @Schema(implementation = Detail_Commande.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Detail_Commande> addDetailCommande(
            @Parameter(description = "Order detail data to create", required = true)
            @Valid @RequestBody Detail_Commande detailCommande) {
        return ResponseEntity.ok(detailCommandeService.addDetailCommande(detailCommande));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple order details", description = "Creates multiple order details in a single operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order details created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<List<Detail_Commande>> addDetailCommandes(
            @Parameter(description = "List of order details to create", required = true)
            @RequestBody List<Detail_Commande> details) {
        return ResponseEntity.ok(detailCommandeService.saveDetailCommandes(details));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order detail by ID", description = "Retrieves a specific order detail by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order detail found",
                    content = @Content(schema = @Schema(implementation = Detail_Commande.class))),
            @ApiResponse(responseCode = "404", description = "Order detail not found", content = @Content)
    })
    public ResponseEntity<Detail_Commande> getDetailCommandeById(
            @Parameter(description = "ID of the order detail to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(detailCommandeService.selectDetailCommandeByIdWithOrElse(id));
    }

    @GetMapping
    @Operation(summary = "Get all order details", description = "Retrieves a list of all order details in the system")
    @ApiResponse(responseCode = "200", description = "List of order details retrieved successfully")
    public ResponseEntity<List<Detail_Commande>> getAllDetailCommandes() {
        return ResponseEntity.ok(detailCommandeService.selectAllDetailCommandes());
    }

    @PutMapping
    @Operation(summary = "Update an order detail", description = "Updates an existing order detail (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order detail updated successfully",
                    content = @Content(schema = @Schema(implementation = Detail_Commande.class))),
            @ApiResponse(responseCode = "404", description = "Order detail not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Detail_Commande> updateDetailCommande(
            @Parameter(description = "Order detail data to update", required = true)
            @RequestBody Detail_Commande detailCommande) {
        return ResponseEntity.ok(detailCommandeService.updateDetailCommande(detailCommande));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update an order detail", description = "Updates specific fields of an existing order detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order detail partially updated successfully",
                    content = @Content(schema = @Schema(implementation = Detail_Commande.class))),
            @ApiResponse(responseCode = "404", description = "Order detail not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Detail_Commande> patchDetailCommande(
            @Parameter(description = "ID of the order detail to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial order detail data to update", required = true)
            @RequestBody Detail_Commande detailCommande) {
        return ResponseEntity.ok(detailCommandeService.patchDetailCommandeById(id, detailCommande));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order detail by ID", description = "Deletes a specific order detail by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order detail deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Order detail not found", content = @Content)
    })
    public ResponseEntity<Void> deleteDetailCommandeById(
            @Parameter(description = "ID of the order detail to delete", required = true)
            @PathVariable Long id) {
        detailCommandeService.deleteDetailCommandeById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete all order details", description = "Deletes all order details from the system")
    @ApiResponse(responseCode = "200", description = "All order details deleted successfully")
    public ResponseEntity<Void> deleteAllDetailCommandes() {
        detailCommandeService.deleteAllDetailCommandes();
        return ResponseEntity.ok().build();
    }
}
