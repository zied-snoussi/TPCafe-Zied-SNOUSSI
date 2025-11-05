package tn.esprit.tpcafeziedsnoussi.services.interfaces;

import tn.esprit.tpcafeziedsnoussi.entities.Promotion;

import java.util.List;

public interface IPromotionService {
    Promotion addPromotion(Promotion promotion);
    List<Promotion> savePromotions(List<Promotion> promotions);
    Promotion selectPromotionByIdWithOrElse(Long id);
    List<Promotion> selectAllPromotions();
    Promotion updatePromotion(Promotion promotion);
    Promotion patchPromotionById(Long id, Promotion promotion);
    void deletePromotion(Promotion promotion);
    void deleteAllPromotions();
    void deletePromotionById(Long id);
    boolean verifPromotionById(Long id);
}
