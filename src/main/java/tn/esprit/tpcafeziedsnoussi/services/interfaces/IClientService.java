package tn.esprit.tpcafeziedsnoussi.services.interfaces;

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
}
