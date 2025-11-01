package tn.esprit.tpcafeziedsnoussi.services.interfaces;

import tn.esprit.tpcafeziedsnoussi.entities.Adresse;

import java.util.List;

public interface IAdressService {
    Adresse addAdress(Adresse adresse);
    List<Adresse> saveAdresses(List<Adresse> adresses);
    Adresse selectAdressByIdWithOrElse(Long id);
    List<Adresse> selectAllAdresses();
    Adresse updateAdress(Adresse adresse);
    void deleteAdress(Adresse adresse);
    void deleteAllAdresses();
    void deleteAdressById(Long id);
    boolean verifAdressById(Long id);
}
