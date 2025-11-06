package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpcafeziedsnoussi.entities.Adresse;

import java.util.List;
import java.util.Optional;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {

    // Find addresses by city
    List<Adresse> findByVille(String ville);

    // Find addresses by city containing (case-insensitive)
    List<Adresse> findByVilleContainingIgnoreCase(String ville);

    // Find addresses by city starting with (ignore case)
    List<Adresse> findByVilleStartingWithIgnoreCase(String prefix);

    // Find addresses by city ending with (ignore case)
    List<Adresse> findByVilleEndingWithIgnoreCase(String suffix);

    // Find addresses by city NOT containing
    @Query("SELECT a FROM Adresse a WHERE a.ville NOT LIKE %:keyword%")
    List<Adresse> findByVilleNotContaining(@Param("keyword") String keyword);

    // Find addresses by city NOT starting with
    @Query("SELECT a FROM Adresse a WHERE a.ville NOT LIKE :prefix%")
    List<Adresse> findByVilleNotStartingWith(@Param("prefix") String prefix);

    // Find addresses by postal code
    List<Adresse> findByCodePostal(String codePostal);

    // Find addresses by postal code NOT equal
    @Query("SELECT a FROM Adresse a WHERE a.codePostal <> :codePostal")
    List<Adresse> findByCodePostalNot(@Param("codePostal") String codePostal);

    // Find addresses by street
    List<Adresse> findByRue(String rue);

    // Find addresses by street containing (case-insensitive)
    List<Adresse> findByRueContainingIgnoreCase(String rue);

    // Find addresses by street starting with (ignore case)
    List<Adresse> findByRueStartingWithIgnoreCase(String prefix);

    // Find addresses by street ending with (ignore case)
    List<Adresse> findByRueEndingWithIgnoreCase(String suffix);

    // Find addresses by street NOT containing
    @Query("SELECT a FROM Adresse a WHERE LOWER(a.rue) NOT LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Adresse> findByRueNotContaining(@Param("keyword") String keyword);

    // Find addresses by city and postal code
    List<Adresse> findByVilleAndCodePostal(String ville, String codePostal);

    // Custom query to find addresses in a specific city with street containing keyword
    @Query("SELECT a FROM Adresse a WHERE a.ville = :ville AND LOWER(a.rue) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Adresse> findByVilleAndRueContaining(@Param("ville") String ville, @Param("keyword") String keyword);

}
