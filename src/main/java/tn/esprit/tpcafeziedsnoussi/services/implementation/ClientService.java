package tn.esprit.tpcafeziedsnoussi.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpcafeziedsnoussi.entities.Adresse;
import tn.esprit.tpcafeziedsnoussi.entities.CarteFidelite;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.exceptions.ResourceNotFoundException;
import tn.esprit.tpcafeziedsnoussi.repositories.AdresseRepository;
import tn.esprit.tpcafeziedsnoussi.repositories.CarteFideliteRepository;
import tn.esprit.tpcafeziedsnoussi.repositories.ClientRepository;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final AdresseRepository adresseRepository;
    private final CarteFideliteRepository carteFideliteRepository;

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> saveClients(List<Client> clients) {
        return clientRepository.saveAll(clients);
    }

    @Override
    public Client selectClientByIdWithOrElse(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
    }

    @Override
    public List<Client> selectAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Client client) {
        if (!clientRepository.existsById(client.getIdClient())) {
            throw new ResourceNotFoundException("Client", "id", client.getIdClient());
        }
        return clientRepository.save(client);
    }

    @Override
    public Client updateClientById(Long id, Client client) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        
        if (client.getNom() != null) {
            existingClient.setNom(client.getNom());
        }
        if (client.getPrenom() != null) {
            existingClient.setPrenom(client.getPrenom());
        }
        if (client.getDateNaissance() != null) {
            existingClient.setDateNaissance(client.getDateNaissance());
        }

        return clientRepository.save(existingClient);
    }

    @Override
    public Client addClinetWithAddress(Client client, Adresse adresse) {
        client.setAdresse(adresse);
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public void deleteAllClients() {
        clientRepository.deleteAll();
    }

    @Override
    public void deleteClientById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", "id", id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public boolean verifClientById(Long id) {
        return clientRepository.existsById(id);
    }

    @Override
    public String affecterAdresseAClient(Long idAdresse, Long idClient) {
        Adresse adresse = adresseRepository.findById(idAdresse)
                .orElseThrow(() -> new ResourceNotFoundException("Adresse", "id", idAdresse));
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", idClient));
        client.setAdresse(adresse);
        clientRepository.save(client);
        return "L'affectation de " + client.getNom() + " " + client.getPrenom() + " à l'adresse "
                + adresse.getVille() + " " + adresse.getRue() + " a été effectuée avec succés ! ";
    }

    @Override
    public void affecterCarteAClient(Long idCarte, Long idClient) {
        CarteFidelite carteFidelite = carteFideliteRepository.findById(idCarte)
                .orElseThrow(() -> new ResourceNotFoundException("CarteFidelite", "id", idCarte));
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", idClient));
        client.setCarteFidelite(carteFidelite);
        clientRepository.save(client);
    }

}

