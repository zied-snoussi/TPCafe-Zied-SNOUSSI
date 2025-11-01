package tn.esprit.tpcafeziedsnoussi.controllers.rest;

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
public class DetailCommandeRestController {

    private final IDetailCommandeService detailCommandeService;

    @PostMapping
    public ResponseEntity<Detail_Commande> addDetailCommande(@RequestBody Detail_Commande detailCommande) {
        return ResponseEntity.ok(detailCommandeService.addDetailCommande(detailCommande));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Detail_Commande>> addDetailCommandes(@RequestBody List<Detail_Commande> details) {
        return ResponseEntity.ok(detailCommandeService.saveDetailCommandes(details));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detail_Commande> getDetailCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(detailCommandeService.selectDetailCommandeByIdWithOrElse(id));
    }

    @GetMapping
    public ResponseEntity<List<Detail_Commande>> getAllDetailCommandes() {
        return ResponseEntity.ok(detailCommandeService.selectAllDetailCommandes());
    }

    @PutMapping
    public ResponseEntity<Detail_Commande> updateDetailCommande(@RequestBody Detail_Commande detailCommande) {
        return ResponseEntity.ok(detailCommandeService.updateDetailCommande(detailCommande));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailCommandeById(@PathVariable Long id) {
        detailCommandeService.deleteDetailCommandeById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllDetailCommandes() {
        detailCommandeService.deleteAllDetailCommandes();
        return ResponseEntity.ok().build();
    }
}
