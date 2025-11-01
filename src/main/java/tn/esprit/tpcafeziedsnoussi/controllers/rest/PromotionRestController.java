package tn.esprit.tpcafeziedsnoussi.controllers.rest;

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
public class PromotionRestController {

    private final IPromotionService promotionService;

    @PostMapping
    public ResponseEntity<Promotion> addPromotion(@RequestBody Promotion promotion) {
        return ResponseEntity.ok(promotionService.addPromotion(promotion));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Promotion>> addPromotions(@RequestBody List<Promotion> promotions) {
        return ResponseEntity.ok(promotionService.savePromotions(promotions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable Long id) {
        return ResponseEntity.ok(promotionService.selectPromotionByIdWithOrElse(id));
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        return ResponseEntity.ok(promotionService.selectAllPromotions());
    }

    @PutMapping
    public ResponseEntity<Promotion> updatePromotion(@RequestBody Promotion promotion) {
        return ResponseEntity.ok(promotionService.updatePromotion(promotion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotionById(@PathVariable Long id) {
        promotionService.deletePromotionById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllPromotions() {
        promotionService.deleteAllPromotions();
        return ResponseEntity.ok().build();
    }
}
