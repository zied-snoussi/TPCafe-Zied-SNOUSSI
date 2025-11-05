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
import tn.esprit.tpcafeziedsnoussi.dtos.PromotionDTO;
import tn.esprit.tpcafeziedsnoussi.mappers.PromotionMapper;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IPromotionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Promotion Management", description = "APIs for managing promotions and discounts in the TPCafe system")
public class PromotionRestController {

    private final IPromotionService promotionService;
    private final PromotionMapper promotionMapper;

    @PostMapping
    @Operation(summary = "Create a new promotion", description = "Creates a new promotion/discount in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotion created successfully",
                    content = @Content(schema = @Schema(implementation = PromotionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<PromotionDTO> addPromotion(
            @Parameter(description = "Promotion data to create", required = true)
            @Valid @RequestBody PromotionDTO promotionDTO) {
        var promotion = promotionMapper.toEntity(promotionDTO);
        var savedPromotion = promotionService.addPromotion(promotion);
        return ResponseEntity.ok(promotionMapper.toDTO(savedPromotion));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple promotions", description = "Creates multiple promotions in a single operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotions created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<List<PromotionDTO>> addPromotions(
            @Parameter(description = "List of promotions to create", required = true)
            @RequestBody List<PromotionDTO> promotionDTOs) {
        var promotions = promotionDTOs.stream()
                .map(promotionMapper::toEntity)
                .collect(Collectors.toList());
        var savedPromotions = promotionService.savePromotions(promotions);
        return ResponseEntity.ok(savedPromotions.stream()
                .map(promotionMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get promotion by ID", description = "Retrieves a specific promotion by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotion found",
                    content = @Content(schema = @Schema(implementation = PromotionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Promotion not found", content = @Content)
    })
    public ResponseEntity<PromotionDTO> getPromotionById(
            @Parameter(description = "ID of the promotion to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(promotionMapper.toDTO(promotionService.selectPromotionByIdWithOrElse(id)));
    }

    @GetMapping
    @Operation(summary = "Get all promotions", description = "Retrieves a list of all promotions in the system")
    @ApiResponse(responseCode = "200", description = "List of promotions retrieved successfully")
    public ResponseEntity<List<PromotionDTO>> getAllPromotions() {
        return ResponseEntity.ok(promotionService.selectAllPromotions().stream()
                .map(promotionMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping
    @Operation(summary = "Update a promotion", description = "Updates an existing promotion (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotion updated successfully",
                    content = @Content(schema = @Schema(implementation = PromotionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Promotion not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<PromotionDTO> updatePromotion(
            @Parameter(description = "Promotion data to update", required = true)
            @RequestBody PromotionDTO promotionDTO) {
        var promotion = promotionMapper.toEntity(promotionDTO);
        var updatedPromotion = promotionService.updatePromotion(promotion);
        return ResponseEntity.ok(promotionMapper.toDTO(updatedPromotion));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update a promotion", description = "Updates specific fields of an existing promotion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotion partially updated successfully",
                    content = @Content(schema = @Schema(implementation = PromotionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Promotion not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<PromotionDTO> patchPromotion(
            @Parameter(description = "ID of the promotion to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial promotion data to update", required = true)
            @RequestBody PromotionDTO promotionDTO) {
        var promotion = promotionMapper.toEntity(promotionDTO);
        var patchedPromotion = promotionService.patchPromotionById(id, promotion);
        return ResponseEntity.ok(promotionMapper.toDTO(patchedPromotion));
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
