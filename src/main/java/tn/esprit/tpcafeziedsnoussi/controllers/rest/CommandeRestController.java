package tn.esprit.tpcafeziedsnoussi.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpcafeziedsnoussi.entities.Commande;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.ICommandeService;

import java.util.List;

@RestController
@RequestMapping("/commandes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommandeRestController {

    private final ICommandeService commandeService;

    @PostMapping
    public ResponseEntity<Commande> addCommande(@RequestBody Commande commande) {
        return ResponseEntity.ok(commandeService.addCommande(commande));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Commande>> addCommandes(@RequestBody List<Commande> commandes) {
        return ResponseEntity.ok(commandeService.saveCommandes(commandes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.selectCommandeByIdWithOrElse(id));
    }

    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.selectAllCommandes());
    }

    @PutMapping
    public ResponseEntity<Commande> updateCommande(@RequestBody Commande commande) {
        return ResponseEntity.ok(commandeService.updateCommande(commande));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommandeById(@PathVariable Long id) {
        commandeService.deleteCommandeById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCommandes() {
        commandeService.deleteAllCommandes();
        return ResponseEntity.ok().build();
    }
}
