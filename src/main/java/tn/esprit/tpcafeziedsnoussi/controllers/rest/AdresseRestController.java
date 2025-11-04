package tn.esprit.tpcafeziedsnoussi.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpcafeziedsnoussi.dtos.AdresseDTO;
import tn.esprit.tpcafeziedsnoussi.mappers.AdresseMapper;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IAdressService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adresses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdresseRestController {

    private final IAdressService adressService;
    private final AdresseMapper adresseMapper;

    @PostMapping
    public ResponseEntity<AdresseDTO> addAdresse(@RequestBody AdresseDTO adresseDTO) {
        var adresse = adresseMapper.toEntity(adresseDTO);
        var savedAdresse = adressService.addAdress(adresse);
        return ResponseEntity.ok(adresseMapper.toDTO(savedAdresse));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<AdresseDTO>> addAdresses(@RequestBody List<AdresseDTO> adresseDTOs) {
        var adresses = adresseDTOs.stream()
                .map(adresseMapper::toEntity)
                .collect(Collectors.toList());
        var savedAdresses = adressService.saveAdresses(adresses);
        return ResponseEntity.ok(savedAdresses.stream()
                .map(adresseMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdresseDTO> getAdresseById(@PathVariable Long id) {
        var adresse = adressService.selectAdressByIdWithOrElse(id);
        return ResponseEntity.ok(adresseMapper.toDTO(adresse));
    }

    @GetMapping
    public ResponseEntity<List<AdresseDTO>> getAllAdresses() {
        var adresses = adressService.selectAllAdresses();
        return ResponseEntity.ok(adresses.stream()
                .map(adresseMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping
    public ResponseEntity<AdresseDTO> updateAdresse(@RequestBody AdresseDTO adresseDTO) {
        var adresse = adresseMapper.toEntity(adresseDTO);
        var updatedAdresse = adressService.updateAdress(adresse);
        return ResponseEntity.ok(adresseMapper.toDTO(updatedAdresse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresseById(@PathVariable Long id) {
        adressService.deleteAdressById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllAdresses() {
        adressService.deleteAllAdresses();
        return ResponseEntity.ok().build();
    }
}
