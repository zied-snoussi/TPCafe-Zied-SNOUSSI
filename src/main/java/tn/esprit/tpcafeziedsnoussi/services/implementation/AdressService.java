package tn.esprit.tpcafeziedsnoussi.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpcafeziedsnoussi.entities.Adresse;
import tn.esprit.tpcafeziedsnoussi.repositories.AdresseRepository;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IAdressService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdressService implements IAdressService {

    private final AdresseRepository adresseRepository;

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
        // return the address if found, otherwise return a default Adresse instance
        return adresseRepository.findById(id).orElse(
                Adresse.builder()
                        .rue("unknown")
                        .ville("unknown")
                        .codePostal("0000")
                        .build()
        );
    }

    @Override
    public List<Adresse> selectAllAdresses() {
        return adresseRepository.findAll();
    }

    @Override
    public Adresse updateAdress(Adresse adresse) {
        if (!adresseRepository.existsById(adresse.getIdAdresse())) {
            throw new RuntimeException("Address not found with id: " + adresse.getIdAdresse());
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
            throw new RuntimeException("Address not found with id: " + id);
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
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        
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
}
