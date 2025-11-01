package tn.esprit.tpcafeziedsnoussi.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpcafeziedsnoussi.entities.Adresse;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IAdressService;

import java.util.List;

@RestController
@RequestMapping("/adresses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdresseRestController {

    private final IAdressService adressService;

    @PostMapping
    public ResponseEntity<Adresse> addAdresse(@RequestBody Adresse adresse) {
        return ResponseEntity.ok(adressService.addAdress(adresse));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Adresse>> addAdresses(@RequestBody List<Adresse> adresses) {
        return ResponseEntity.ok(adressService.saveAdresses(adresses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable Long id) {
        return ResponseEntity.ok(adressService.selectAdressByIdWithOrElse(id));
    }

    @GetMapping
    public ResponseEntity<List<Adresse>> getAllAdresses() {
        return ResponseEntity.ok(adressService.selectAllAdresses());
    }

    @PutMapping
    public ResponseEntity<Adresse> updateAdresse(@RequestBody Adresse adresse) {
        return ResponseEntity.ok(adressService.updateAdress(adresse));
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
