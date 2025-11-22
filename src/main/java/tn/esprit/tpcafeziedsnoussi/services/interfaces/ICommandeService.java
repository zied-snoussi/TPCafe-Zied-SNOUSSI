package tn.esprit.tpcafeziedsnoussi.services.interfaces;

import tn.esprit.tpcafeziedsnoussi.entities.Commande;

import java.util.List;

public interface ICommandeService {
    Commande addCommande(Commande commande);
    List<Commande> saveCommandes(List<Commande> commandes);
    Commande selectCommandeByIdWithOrElse(Long id);
    List<Commande> selectAllCommandes();
    Commande updateCommande(Commande commande);
    Commande patchCommandeById(Long id, Commande commande);
    void deleteCommande(Commande commande);
    void deleteAllCommandes();
    void deleteCommandeById(Long id);
    boolean verifCommandeById(Long id);
    void affecterCommandeToClient(long idCommande, long idClient);
    void desaffecterClientdeCommande(Long idCommande);
}
