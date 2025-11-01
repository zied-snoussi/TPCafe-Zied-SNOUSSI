package tn.esprit.tpcafeziedsnoussi.services.interfaces;

import tn.esprit.tpcafeziedsnoussi.entities.Detail_Commande;

import java.util.List;

public interface IDetailCommandeService {
    Detail_Commande addDetailCommande(Detail_Commande detailCommande);
    List<Detail_Commande> saveDetailCommandes(List<Detail_Commande> details);
    Detail_Commande selectDetailCommandeByIdWithOrElse(Long id);
    List<Detail_Commande> selectAllDetailCommandes();
    Detail_Commande updateDetailCommande(Detail_Commande detailCommande);
    void deleteDetailCommande(Detail_Commande detailCommande);
    void deleteAllDetailCommandes();
    void deleteDetailCommandeById(Long id);
    boolean verifDetailCommandeById(Long id);
}
