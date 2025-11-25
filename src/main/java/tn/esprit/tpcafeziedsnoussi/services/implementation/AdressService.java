package tn.esprit.tpcafeziedsnoussi.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpcafeziedsnoussi.entities.Adresse;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.exceptions.ResourceNotFoundException;
import tn.esprit.tpcafeziedsnoussi.repositories.AdresseRepository;
import tn.esprit.tpcafeziedsnoussi.repositories.ClientRepository;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IAdressService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdressService implements IAdressService {

    private final AdresseRepository adresseRepository;
    private final ClientRepository clientRepository;

    @Override
    public Adresse addAdress(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    @Override
    public List<Adresse> saveAdresses(List<Adresse> adresses) {
        return adresseRepository.saveAll(adresses);
    }

    @Override
    public Adresse selectAdressByIdWithOrElse(Long id) {
        return adresseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adresse", "id", id));
    }

    @Override
    public List<Adresse> selectAllAdresses() {
        return adresseRepository.findAll();
    }

    @Override
    public Adresse updateAdress(Adresse adresse) {
        if (!adresseRepository.existsById(adresse.getIdAdresse())) {
            throw new ResourceNotFoundException("Adresse", "id", adresse.getIdAdresse());
        }
        return adresseRepository.save(adresse);
    }

    @Override
    public void deleteAdress(Adresse adresse) {
        adresseRepository.delete(adresse);
    }

    @Override
    public void deleteAllAdresses() {
        adresseRepository.deleteAll();
    }

    @Override
    public void deleteAdressById(Long id) {
        if (!adresseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Adresse", "id", id);
        }
        adresseRepository.deleteById(id);
    }

    @Override
    public boolean verifAdressById(Long id) {
        return adresseRepository.existsById(id);
    }

    @Override
    public Adresse patchAdressById(Long id, Adresse adresse) {
        Adresse existingAdresse = adresseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adresse", "id", id));
        
        if (adresse.getRue() != null) {
            existingAdresse.setRue(adresse.getRue());
        }
        if (adresse.getVille() != null) {
            existingAdresse.setVille(adresse.getVille());
        }
        if (adresse.getCodePostal() != null) {
            existingAdresse.setCodePostal(adresse.getCodePostal());
        }
        
        return adresseRepository.save(existingAdresse);
    }

    @Override
    public void ajouterEtAffecterAdressAClient(Adresse adresse, Client client) {
        Client existingClient = clientRepository.findById(client.getIdClient())
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", client.getIdClient()));

        Adresse savedAdresse = adresseRepository.save(adresse);
        existingClient.setAdresse(savedAdresse);
        clientRepository.save(existingClient);
    }
}
