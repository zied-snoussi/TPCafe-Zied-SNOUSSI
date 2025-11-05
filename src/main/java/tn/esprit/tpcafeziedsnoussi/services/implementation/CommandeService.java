package tn.esprit.tpcafeziedsnoussi.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpcafeziedsnoussi.entities.Commande;
import tn.esprit.tpcafeziedsnoussi.repositories.CommandeRepository;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.ICommandeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeService implements ICommandeService {

    private final CommandeRepository commandeRepository;

    @Override
    public Commande addCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public List<Commande> saveCommandes(List<Commande> commandes) {
        return commandeRepository.saveAll(commandes);
    }

    @Override
    public Commande selectCommandeByIdWithOrElse(Long id) {
        return commandeRepository.findById(id).orElse(
                Commande.builder()
                        .totalCommande(0f)
                        .build()
        );
    }

    @Override
    public List<Commande> selectAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande updateCommande(Commande commande) {
        if (!commandeRepository.existsById(commande.getIdCommande())) {
            throw new RuntimeException("Commande not found with id: " + commande.getIdCommande());
        }
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteCommande(Commande commande) {
        commandeRepository.delete(commande);
    }

    @Override
    public void deleteAllCommandes() {
        commandeRepository.deleteAll();
    }

    @Override
    public void deleteCommandeById(Long id) {
        if (!commandeRepository.existsById(id)) {
            throw new RuntimeException("Commande not found with id: " + id);
        }
        commandeRepository.deleteById(id);
    }

    @Override
    public boolean verifCommandeById(Long id) {
        return commandeRepository.existsById(id);
    }

    @Override
    public Commande patchCommandeById(Long id, Commande commande) {
        Commande existingCommande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande not found with id: " + id));
        
        if (commande.getDateCommande() != null) {
            existingCommande.setDateCommande(commande.getDateCommande());
        }
        if (commande.getTotalCommande() != 0) {
            existingCommande.setTotalCommande(commande.getTotalCommande());
        }
        if (commande.getStatusCommande() != null) {
            existingCommande.setStatusCommande(commande.getStatusCommande());
        }
        
        return commandeRepository.save(existingCommande);
    }
}

