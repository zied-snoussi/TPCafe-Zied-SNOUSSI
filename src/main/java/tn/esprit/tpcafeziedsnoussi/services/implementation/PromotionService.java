package tn.esprit.tpcafeziedsnoussi.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpcafeziedsnoussi.entities.Promotion;
import tn.esprit.tpcafeziedsnoussi.repositories.PromotionRepository;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IPromotionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService implements IPromotionService {

    private final PromotionRepository promotionRepository;

    @Override
    public Promotion addPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public List<Promotion> savePromotions(List<Promotion> promotions) {
        return promotionRepository.saveAll(promotions);
    }

    @Override
    public Promotion selectPromotionByIdWithOrElse(Long id) {
        return promotionRepository.findById(id).orElse(
                Promotion.builder()
                        .pourcentagePromo(0f)
                        .build()
        );
    }

    @Override
    public List<Promotion> selectAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion updatePromotion(Promotion promotion) {
        if (!promotionRepository.existsById(promotion.getIdPromotion())) {
            throw new RuntimeException("Promotion not found with id: " + promotion.getIdPromotion());
        }
        return promotionRepository.save(promotion);
    }

    @Override
    public void deletePromotion(Promotion promotion) {
        promotionRepository.delete(promotion);
    }

    @Override
    public void deleteAllPromotions() {
        promotionRepository.deleteAll();
    }

    @Override
    public void deletePromotionById(Long id) {
        if (!promotionRepository.existsById(id)) {
            throw new RuntimeException("Promotion not found with id: " + id);
        }
        promotionRepository.deleteById(id);
    }

    @Override
    public boolean verifPromotionById(Long id) {
        return promotionRepository.existsById(id);
    }

    @Override
    public Promotion patchPromotionById(Long id, Promotion promotion) {
        Promotion existingPromotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + id));
        
        if (promotion.getPourcentagePromo() != 0) {
            existingPromotion.setPourcentagePromo(promotion.getPourcentagePromo());
        }
        if (promotion.getDateDebutPromo() != null) {
            existingPromotion.setDateDebutPromo(promotion.getDateDebutPromo());
        }
        if (promotion.getDateFinPromo() != null) {
            existingPromotion.setDateFinPromo(promotion.getDateFinPromo());
        }
        
        return promotionRepository.save(existingPromotion);
    }
}

