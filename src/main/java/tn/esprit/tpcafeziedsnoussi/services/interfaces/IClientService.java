package tn.esprit.tpcafeziedsnoussi.services.interfaces;

import tn.esprit.tpcafeziedsnoussi.entities.Adresse;
import tn.esprit.tpcafeziedsnoussi.entities.CarteFidelite;
import tn.esprit.tpcafeziedsnoussi.entities.Client;

import java.util.List;

public interface IClientService {
    Client addClient(Client client);
    List<Client> saveClients(List<Client> clients);
    Client selectClientByIdWithOrElse(Long id);
    List<Client> selectAllClients();
    Client updateClient(Client client);
    void deleteClient(Client client);
    void deleteAllClients();
    void deleteClientById(Long id);
    boolean verifClientById(Long id);
    Client updateClientById(Long id, Client client);
    Client addClinetWithAddress(Client client, Adresse adresse);
    String affecterAdresseAClient(Long idAdresse, Long idClient);
    void affecterCarteAClient(Long idCarte, Long idClient);
    void ajouterClientEtAffecterCarteFidelite(CarteFidelite carte);
}
