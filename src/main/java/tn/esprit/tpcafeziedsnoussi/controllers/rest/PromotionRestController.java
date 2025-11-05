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
import tn.esprit.tpcafeziedsnoussi.entities.Promotion;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IPromotionService;

import java.util.List;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Promotion Management", description = "APIs for managing promotions and discounts in the TPCafe system")
public class PromotionRestController {

    private final IPromotionService promotionService;

    @PostMapping
    @Operation(summary = "Create a new promotion", description = "Creates a new promotion/discount in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotion created successfully",
                    content = @Content(schema = @Schema(implementation = Promotion.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Promotion> addPromotion(
            @Parameter(description = "Promotion data to create", required = true)
            @Valid @RequestBody Promotion promotion) {
        return ResponseEntity.ok(promotionService.addPromotion(promotion));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple promotions", description = "Creates multiple promotions in a single operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotions created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<List<Promotion>> addPromotions(
            @Parameter(description = "List of promotions to create", required = true)
            @RequestBody List<Promotion> promotions) {
        return ResponseEntity.ok(promotionService.savePromotions(promotions));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get promotion by ID", description = "Retrieves a specific promotion by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotion found",
                    content = @Content(schema = @Schema(implementation = Promotion.class))),
            @ApiResponse(responseCode = "404", description = "Promotion not found", content = @Content)
    })
    public ResponseEntity<Promotion> getPromotionById(
            @Parameter(description = "ID of the promotion to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(promotionService.selectPromotionByIdWithOrElse(id));
    }

    @GetMapping
    @Operation(summary = "Get all promotions", description = "Retrieves a list of all promotions in the system")
    @ApiResponse(responseCode = "200", description = "List of promotions retrieved successfully")
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        return ResponseEntity.ok(promotionService.selectAllPromotions());
    }

    @PutMapping
    @Operation(summary = "Update a promotion", description = "Updates an existing promotion (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotion updated successfully",
                    content = @Content(schema = @Schema(implementation = Promotion.class))),
            @ApiResponse(responseCode = "404", description = "Promotion not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Promotion> updatePromotion(
            @Parameter(description = "Promotion data to update", required = true)
            @RequestBody Promotion promotion) {
        return ResponseEntity.ok(promotionService.updatePromotion(promotion));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update a promotion", description = "Updates specific fields of an existing promotion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotion partially updated successfully",
                    content = @Content(schema = @Schema(implementation = Promotion.class))),
            @ApiResponse(responseCode = "404", description = "Promotion not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Promotion> patchPromotion(
            @Parameter(description = "ID of the promotion to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial promotion data to update", required = true)
            @RequestBody Promotion promotion) {
        return ResponseEntity.ok(promotionService.patchPromotionById(id, promotion));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete promotion by ID", description = "Deletes a specific promotion by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotion deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Promotion not found", content = @Content)
    })
    public ResponseEntity<Void> deletePromotionById(
            @Parameter(description = "ID of the promotion to delete", required = true)
            @PathVariable Long id) {
        promotionService.deletePromotionById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete all promotions", description = "Deletes all promotions from the system")
    @ApiResponse(responseCode = "200", description = "All promotions deleted successfully")
    public ResponseEntity<Void> deleteAllPromotions() {
        promotionService.deleteAllPromotions();
        return ResponseEntity.ok().build();
    }
}
