package tn.esprit.tpcafeziedsnoussi.controllers.rest;

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
public class CarteFideliteRestController {

    private final ICarteFideliteService carteFideliteService;

    @PostMapping
    public ResponseEntity<CarteFidelite> addCarteFidelite(@RequestBody CarteFidelite carteFidelite) {
        return ResponseEntity.ok(carteFideliteService.addCarteFidelite(carteFidelite));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<CarteFidelite>> addCartesFidelite(@RequestBody List<CarteFidelite> cartes) {
        return ResponseEntity.ok(carteFideliteService.saveCartesFidelite(cartes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteFidelite> getCarteFideliteById(@PathVariable Long id) {
        return ResponseEntity.ok(carteFideliteService.selectCarteFideliteByIdWithOrElse(id));
    }

    @GetMapping
    public ResponseEntity<List<CarteFidelite>> getAllCartesFidelite() {
        return ResponseEntity.ok(carteFideliteService.selectAllCartesFidelite());
    }

    @PutMapping
    public ResponseEntity<CarteFidelite> updateCarteFidelite(@RequestBody CarteFidelite carteFidelite) {
        return ResponseEntity.ok(carteFideliteService.updateCarteFidelite(carteFidelite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarteFideliteById(@PathVariable Long id) {
        carteFideliteService.deleteCarteFideliteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCartesFidelite() {
        carteFideliteService.deleteAllCartesFidelite();
        return ResponseEntity.ok().build();
    }
}
