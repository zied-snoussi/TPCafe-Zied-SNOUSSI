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
import tn.esprit.tpcafeziedsnoussi.dtos.DetailCommandeDTO;
import tn.esprit.tpcafeziedsnoussi.mappers.DetailCommandeMapper;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IDetailCommandeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detail-commandes")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Order Detail Management", description = "APIs for managing order details/line items in the TPCafe system")
public class DetailCommandeRestController {

    private final IDetailCommandeService detailCommandeService;
    private final DetailCommandeMapper detailCommandeMapper;

    @PostMapping
    @Operation(summary = "Create a new order detail", description = "Creates a new order detail/line item in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order detail created successfully",
                    content = @Content(schema = @Schema(implementation = DetailCommandeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<DetailCommandeDTO> addDetailCommande(
            @Parameter(description = "Order detail data to create", required = true)
            @Valid @RequestBody DetailCommandeDTO detailCommandeDTO) {
        var detailCommande = detailCommandeMapper.toEntity(detailCommandeDTO);
        var savedDetail = detailCommandeService.addDetailCommande(detailCommande);
        return ResponseEntity.ok(detailCommandeMapper.toDTO(savedDetail));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple order details", description = "Creates multiple order details in a single operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order details created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<List<DetailCommandeDTO>> addDetailCommandes(
            @Parameter(description = "List of order details to create", required = true)
            @RequestBody List<DetailCommandeDTO> detailDTOs) {
        var details = detailDTOs.stream()
                .map(detailCommandeMapper::toEntity)
                .collect(Collectors.toList());
        var savedDetails = detailCommandeService.saveDetailCommandes(details);
        return ResponseEntity.ok(savedDetails.stream()
                .map(detailCommandeMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order detail by ID", description = "Retrieves a specific order detail by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order detail found",
                    content = @Content(schema = @Schema(implementation = DetailCommandeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Order detail not found", content = @Content)
    })
    public ResponseEntity<DetailCommandeDTO> getDetailCommandeById(
            @Parameter(description = "ID of the order detail to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(detailCommandeMapper.toDTO(detailCommandeService.selectDetailCommandeByIdWithOrElse(id)));
    }

    @GetMapping
    @Operation(summary = "Get all order details", description = "Retrieves a list of all order details in the system")
    @ApiResponse(responseCode = "200", description = "List of order details retrieved successfully")
    public ResponseEntity<List<DetailCommandeDTO>> getAllDetailCommandes() {
        return ResponseEntity.ok(detailCommandeService.selectAllDetailCommandes().stream()
                .map(detailCommandeMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping
    @Operation(summary = "Update an order detail", description = "Updates an existing order detail (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order detail updated successfully",
                    content = @Content(schema = @Schema(implementation = DetailCommandeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Order detail not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<DetailCommandeDTO> updateDetailCommande(
            @Parameter(description = "Order detail data to update", required = true)
            @RequestBody DetailCommandeDTO detailCommandeDTO) {
        var detailCommande = detailCommandeMapper.toEntity(detailCommandeDTO);
        var updatedDetail = detailCommandeService.updateDetailCommande(detailCommande);
        return ResponseEntity.ok(detailCommandeMapper.toDTO(updatedDetail));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update an order detail", description = "Updates specific fields of an existing order detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order detail partially updated successfully",
                    content = @Content(schema = @Schema(implementation = DetailCommandeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Order detail not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<DetailCommandeDTO> patchDetailCommande(
            @Parameter(description = "ID of the order detail to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial order detail data to update", required = true)
            @RequestBody DetailCommandeDTO detailCommandeDTO) {
        var detailCommande = detailCommandeMapper.toEntity(detailCommandeDTO);
        var patchedDetail = detailCommandeService.patchDetailCommandeById(id, detailCommande);
        return ResponseEntity.ok(detailCommandeMapper.toDTO(patchedDetail));
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
