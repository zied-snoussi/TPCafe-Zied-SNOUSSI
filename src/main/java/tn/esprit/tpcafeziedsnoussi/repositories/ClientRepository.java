package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpcafeziedsnoussi.entities.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    // Find clients by last name
    List<Client> findByNom(String nom);

    // Find clients by first name
    List<Client> findByPrenom(String prenom);

    // Find clients by last name and first name
    List<Client> findByNomAndPrenom(String nom, String prenom);

    // Find clients by last name containing (case-insensitive)
    List<Client> findByNomContainingIgnoreCase(String nom);

    // Find clients by last name starting with (ignore case)
    List<Client> findByNomStartingWithIgnoreCase(String prefix);

    // Find clients by last name ending with (ignore case)
    List<Client> findByNomEndingWithIgnoreCase(String suffix);

    // Find clients by last name NOT containing
    @Query("SELECT c FROM Client c WHERE c.nom NOT LIKE %:keyword%")
    List<Client> findByNomNotContaining(@Param("keyword") String keyword);

    // Find clients by last name NOT starting with
    @Query("SELECT c FROM Client c WHERE c.nom NOT LIKE :prefix%")
    List<Client> findByNomNotStartingWith(@Param("prefix") String prefix);

    // Find clients by birth date
    List<Client> findByDateNaissance(LocalDate dateNaissance);

    // Find clients born after a specific date
    List<Client> findByDateNaissanceAfter(LocalDate date);

    // Find clients born before a specific date
    List<Client> findByDateNaissanceBefore(LocalDate date);

    // Find clients born between dates
    List<Client> findByDateNaissanceBetween(LocalDate startDate, LocalDate endDate);

    // Find clients NOT born on a specific date
    @Query("SELECT c FROM Client c WHERE c.dateNaissance <> :date")
    List<Client> findByDateNaissanceNot(@Param("date") LocalDate date);

    // Custom query to find clients by city (through address)
    @Query("SELECT c FROM Client c WHERE c.adresse.ville = :ville")
    List<Client> findByVille(@Param("ville") String ville);

    // Custom query to find clients with loyalty card points greater than
    @Query("SELECT c FROM Client c WHERE c.carteFidelite.pointsAccumules > :points")
    List<Client> findByPointsAccumulesGreaterThan(@Param("points") int points);

    // Find clients with loyalty card points NOT equal to
    @Query("SELECT c FROM Client c WHERE c.carteFidelite.pointsAccumules <> :points")
    List<Client> findByPointsAccumulesNot(@Param("points") int points);

}

