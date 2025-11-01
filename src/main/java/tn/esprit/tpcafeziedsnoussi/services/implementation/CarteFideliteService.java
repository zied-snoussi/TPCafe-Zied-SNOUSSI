package tn.esprit.tpcafeziedsnoussi.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpcafeziedsnoussi.entities.CarteFidelite;
import tn.esprit.tpcafeziedsnoussi.repositories.CarteFideliteRepository;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.ICarteFideliteService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarteFideliteService implements ICarteFideliteService {

    private final CarteFideliteRepository carteFideliteRepository;

    @Override
    public CarteFidelite addCarteFidelite(CarteFidelite carteFidelite) {
        return carteFideliteRepository.save(carteFidelite);
    }

    @Override
    public List<CarteFidelite> saveCartesFidelite(List<CarteFidelite> cartes) {
        return carteFideliteRepository.saveAll(cartes);
    }

    @Override
    public CarteFidelite selectCarteFideliteByIdWithOrElse(Long id) {
        return carteFideliteRepository.findById(id).orElse(
                CarteFidelite.builder()
                        .pointsAccumules(0)
                        .build()
        );
    }

    @Override
    public List<CarteFidelite> selectAllCartesFidelite() {
        return carteFideliteRepository.findAll();
    }

    @Override
    public CarteFidelite updateCarteFidelite(CarteFidelite carteFidelite) {
        if (!carteFideliteRepository.existsById(carteFidelite.getIdCarteFidelite())) {
            throw new RuntimeException("CarteFidelite not found with id: " + carteFidelite.getIdCarteFidelite());
        }
        return carteFideliteRepository.save(carteFidelite);
    }

    @Override
    public void deleteCarteFidelite(CarteFidelite carteFidelite) {
        carteFideliteRepository.delete(carteFidelite);
    }

    @Override
    public void deleteAllCartesFidelite() {
        carteFideliteRepository.deleteAll();
    }

    @Override
    public void deleteCarteFideliteById(Long id) {
        if (!carteFideliteRepository.existsById(id)) {
            throw new RuntimeException("CarteFidelite not found with id: " + id);
        }
        carteFideliteRepository.deleteById(id);
    }

    @Override
    public boolean verifCarteFideliteById(Long id) {
        return carteFideliteRepository.existsById(id);
    }
}

