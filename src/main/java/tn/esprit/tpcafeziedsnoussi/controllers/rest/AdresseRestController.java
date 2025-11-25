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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpcafeziedsnoussi.dtos.AdresseDTO;
import tn.esprit.tpcafeziedsnoussi.mappers.AdresseMapper;
import tn.esprit.tpcafeziedsnoussi.mappers.ClientMapper;
import tn.esprit.tpcafeziedsnoussi.services.implementation.ClientService;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IAdressService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adresses")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Address Management", description = "APIs for managing addresses in the TPCafe system")
public class AdresseRestController {

    private final IAdressService adressService;
    private final AdresseMapper adresseMapper;
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    @Operation(summary = "Create a new address", description = "Creates a new address in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address created successfully",
                    content = @Content(schema = @Schema(implementation = AdresseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<AdresseDTO> addAdresse(
            @Parameter(description = "Address data to create", required = true)
            @Valid @RequestBody AdresseDTO adresseDTO) {
        var adresse = adresseMapper.toEntity(adresseDTO);
        var savedAdresse = adressService.addAdress(adresse);
        return ResponseEntity.ok(adresseMapper.toDTO(savedAdresse));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple addresses", description = "Creates multiple addresses in a single operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Addresses created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<List<AdresseDTO>> addAdresses(
            @Parameter(description = "List of addresses to create", required = true)
            @RequestBody List<AdresseDTO> adresseDTOs) {
        var adresses = adresseDTOs.stream()
                .map(adresseMapper::toEntity)
                .collect(Collectors.toList());
        var savedAdresses = adressService.saveAdresses(adresses);
        return ResponseEntity.ok(savedAdresses.stream()
                .map(adresseMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get address by ID", description = "Retrieves a specific address by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address found",
                    content = @Content(schema = @Schema(implementation = AdresseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content)
    })
    public ResponseEntity<AdresseDTO> getAdresseById(
            @Parameter(description = "ID of the address to retrieve", required = true)
            @PathVariable Long id) {
        var adresse = adressService.selectAdressByIdWithOrElse(id);
        return ResponseEntity.ok(adresseMapper.toDTO(adresse));
    }

    @GetMapping
    @Operation(summary = "Get all addresses", description = "Retrieves a list of all addresses in the system")
    @ApiResponse(responseCode = "200", description = "List of addresses retrieved successfully")
    public ResponseEntity<List<AdresseDTO>> getAllAdresses() {
        var adresses = adressService.selectAllAdresses();
        return ResponseEntity.ok(adresses.stream()
                .map(adresseMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping
    @Operation(summary = "Update an address", description = "Updates an existing address (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully",
                    content = @Content(schema = @Schema(implementation = AdresseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<AdresseDTO> updateAdresse(
            @Parameter(description = "Address data to update", required = true)
            @RequestBody AdresseDTO adresseDTO) {
        var adresse = adresseMapper.toEntity(adresseDTO);
        var updatedAdresse = adressService.updateAdress(adresse);
        return ResponseEntity.ok(adresseMapper.toDTO(updatedAdresse));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update an address", description = "Updates specific fields of an existing address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address partially updated successfully",
                    content = @Content(schema = @Schema(implementation = AdresseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<AdresseDTO> patchAdresse(
            @Parameter(description = "ID of the address to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial address data to update", required = true)
            @RequestBody AdresseDTO adresseDTO) {
        var adresse = adresseMapper.toEntity(adresseDTO);
        var patchedAdresse = adressService.patchAdressById(id, adresse);
        return ResponseEntity.ok(adresseMapper.toDTO(patchedAdresse));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete address by ID", description = "Deletes a specific address by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content)
    })
    public ResponseEntity<Void> deleteAdresseById(
            @Parameter(description = "ID of the address to delete", required = true)
            @PathVariable Long id) {
        adressService.deleteAdressById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete all addresses", description = "Deletes all addresses from the system")
    @ApiResponse(responseCode = "200", description = "All addresses deleted successfully")
    public ResponseEntity<Void> deleteAllAdresses() {
        adressService.deleteAllAdresses();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/assign-to-client")
    @Operation(summary = "Add and assign address to client", description = "Creates a new address and assigns it to a specific client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address created and assigned successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<Void> ajouterEtAffecterAdressAClient(
            @Parameter(description = "Address data to create", required = true)
            @Valid @RequestBody AdresseDTO adresseDTO,
            @Parameter(description = "Client data to assign the address to", required = true)
            @Valid @RequestBody tn.esprit.tpcafeziedsnoussi.dtos.ClientDTO clientDTO) {
        var adresse = adresseMapper.toEntity(adresseDTO);
        var client = clientMapper.toEntity(clientDTO);
        adressService.ajouterEtAffecterAdressAClient(adresse, client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
