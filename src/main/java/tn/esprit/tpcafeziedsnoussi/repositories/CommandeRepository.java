package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpcafeziedsnoussi.entities.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

}

