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
import tn.esprit.tpcafeziedsnoussi.entities.CarteFidelite;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.ICarteFideliteService;

import java.util.List;

@RestController
@RequestMapping("/cartes-fidelite")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Loyalty Card Management", description = "APIs for managing customer loyalty cards in the TPCafe system")
public class CarteFideliteRestController {

    private final ICarteFideliteService carteFideliteService;

    @PostMapping
    @Operation(summary = "Create a new loyalty card", description = "Creates a new loyalty card for a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loyalty card created successfully",
                    content = @Content(schema = @Schema(implementation = CarteFidelite.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<CarteFidelite> addCarteFidelite(
            @Parameter(description = "Loyalty card data to create", required = true)
            @Valid @RequestBody CarteFidelite carteFidelite) {
        return ResponseEntity.ok(carteFideliteService.addCarteFidelite(carteFidelite));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple loyalty cards", description = "Creates multiple loyalty cards in a single operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loyalty cards created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<List<CarteFidelite>> addCartesFidelite(
            @Parameter(description = "List of loyalty cards to create", required = true)
            @RequestBody List<CarteFidelite> cartes) {
        return ResponseEntity.ok(carteFideliteService.saveCartesFidelite(cartes));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get loyalty card by ID", description = "Retrieves a specific loyalty card by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loyalty card found",
                    content = @Content(schema = @Schema(implementation = CarteFidelite.class))),
            @ApiResponse(responseCode = "404", description = "Loyalty card not found", content = @Content)
    })
    public ResponseEntity<CarteFidelite> getCarteFideliteById(
            @Parameter(description = "ID of the loyalty card to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(carteFideliteService.selectCarteFideliteByIdWithOrElse(id));
    }

    @GetMapping
    @Operation(summary = "Get all loyalty cards", description = "Retrieves a list of all loyalty cards in the system")
    @ApiResponse(responseCode = "200", description = "List of loyalty cards retrieved successfully")
    public ResponseEntity<List<CarteFidelite>> getAllCartesFidelite() {
        return ResponseEntity.ok(carteFideliteService.selectAllCartesFidelite());
    }

    @PutMapping
    @Operation(summary = "Update a loyalty card", description = "Updates an existing loyalty card (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loyalty card updated successfully",
                    content = @Content(schema = @Schema(implementation = CarteFidelite.class))),
            @ApiResponse(responseCode = "404", description = "Loyalty card not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<CarteFidelite> updateCarteFidelite(
            @Parameter(description = "Loyalty card data to update", required = true)
            @RequestBody CarteFidelite carteFidelite) {
        return ResponseEntity.ok(carteFideliteService.updateCarteFidelite(carteFidelite));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update a loyalty card", description = "Updates specific fields of an existing loyalty card")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loyalty card partially updated successfully",
                    content = @Content(schema = @Schema(implementation = CarteFidelite.class))),
            @ApiResponse(responseCode = "404", description = "Loyalty card not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<CarteFidelite> patchCarteFidelite(
            @Parameter(description = "ID of the loyalty card to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial loyalty card data to update", required = true)
            @RequestBody CarteFidelite carteFidelite) {
        return ResponseEntity.ok(carteFideliteService.patchCarteFideliteById(id, carteFidelite));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete loyalty card by ID", description = "Deletes a specific loyalty card by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loyalty card deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Loyalty card not found", content = @Content)
    })
    public ResponseEntity<Void> deleteCarteFideliteById(
            @Parameter(description = "ID of the loyalty card to delete", required = true)
            @PathVariable Long id) {
        carteFideliteService.deleteCarteFideliteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete all loyalty cards", description = "Deletes all loyalty cards from the system")
    @ApiResponse(responseCode = "200", description = "All loyalty cards deleted successfully")
    public ResponseEntity<Void> deleteAllCartesFidelite() {
        carteFideliteService.deleteAllCartesFidelite();
        return ResponseEntity.ok().build();
    }
}
