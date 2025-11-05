package tn.esprit.tpcafeziedsnoussi.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpcafeziedsnoussi.entities.Detail_Commande;
import tn.esprit.tpcafeziedsnoussi.exceptions.ResourceNotFoundException;
import tn.esprit.tpcafeziedsnoussi.repositories.Detail_CommandeRepository;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IDetailCommandeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailCommandeService implements IDetailCommandeService {

    private final Detail_CommandeRepository detailCommandeRepository;

    @Override
    public Detail_Commande addDetailCommande(Detail_Commande detailCommande) {
        return detailCommandeRepository.save(detailCommande);
    }

    @Override
    public List<Detail_Commande> saveDetailCommandes(List<Detail_Commande> details) {
        return detailCommandeRepository.saveAll(details);
    }

    @Override
    public Detail_Commande selectDetailCommandeByIdWithOrElse(Long id) {
        return detailCommandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detail_Commande", "id", id));
    }

    @Override
    public List<Detail_Commande> selectAllDetailCommandes() {
        return detailCommandeRepository.findAll();
    }

    @Override
    public Detail_Commande updateDetailCommande(Detail_Commande detailCommande) {
        if (!detailCommandeRepository.existsById(detailCommande.getIdDetailCommande())) {
            throw new ResourceNotFoundException("Detail_Commande", "id", detailCommande.getIdDetailCommande());
        }
        return detailCommandeRepository.save(detailCommande);
    }

    @Override
    public void deleteDetailCommande(Detail_Commande detailCommande) {
        detailCommandeRepository.delete(detailCommande);
    }

    @Override
    public void deleteAllDetailCommandes() {
        detailCommandeRepository.deleteAll();
    }

    @Override
    public void deleteDetailCommandeById(Long id) {
        if (!detailCommandeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Detail_Commande", "id", id);
        }
        detailCommandeRepository.deleteById(id);
    }

    @Override
    public boolean verifDetailCommandeById(Long id) {
        return detailCommandeRepository.existsById(id);
    }

    @Override
    public Detail_Commande patchDetailCommandeById(Long id, Detail_Commande detailCommande) {
        Detail_Commande existingDetail = detailCommandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detail_Commande", "id", id));
        
        if (detailCommande.getQuantiteArticle() != 0) {
            existingDetail.setQuantiteArticle(detailCommande.getQuantiteArticle());
        }
        if (detailCommande.getSousTotalDetailArticle() != 0) {
            existingDetail.setSousTotalDetailArticle(detailCommande.getSousTotalDetailArticle());
        }
        if (detailCommande.getSousTotalDetailArticleApresPromo() != 0) {
            existingDetail.setSousTotalDetailArticleApresPromo(detailCommande.getSousTotalDetailArticleApresPromo());
        }
        
        return detailCommandeRepository.save(existingDetail);
    }
}

