package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpcafeziedsnoussi.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

