package tn.esprit.tpcafeziedsnoussi.services.interfaces;

import tn.esprit.tpcafeziedsnoussi.entities.CarteFidelite;

import java.util.List;

public interface ICarteFideliteService {
    CarteFidelite addCarteFidelite(CarteFidelite carteFidelite);
    List<CarteFidelite> saveCartesFidelite(List<CarteFidelite> cartes);
    CarteFidelite selectCarteFideliteByIdWithOrElse(Long id);
    List<CarteFidelite> selectAllCartesFidelite();
    CarteFidelite updateCarteFidelite(CarteFidelite carteFidelite);
    void deleteCarteFidelite(CarteFidelite carteFidelite);
    void deleteAllCartesFidelite();
    void deleteCarteFideliteById(Long id);
    boolean verifCarteFideliteById(Long id);
}
