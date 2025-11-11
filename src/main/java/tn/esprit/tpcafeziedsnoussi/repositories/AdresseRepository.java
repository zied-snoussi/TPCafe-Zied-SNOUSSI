package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpcafeziedsnoussi.entities.Adresse;

import java.util.List;
import java.util.Optional;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {


    List<Adresse> findByVille(String ville);
    @Query("SELECT a FROM Adresse a WHERE a.ville = :ville")
    List<Adresse> findByVilleJPQL(@Param("ville") String ville);
    @Query(value = "SELECT * FROM adresse WHERE ville = :ville", nativeQuery = true)
    List<Adresse> findByVilleNative(@Param("ville") String ville);

    List<Adresse> findByCodePostal(String codePostal);
    @Query("SELECT a FROM Adresse a WHERE a.codePostal = :codePostal")
    List<Adresse> findByCodePostalJPQL(@Param("codePostal") String codePostal);
    @Query(value = "SELECT * FROM adresse WHERE code_postal = :codePostal", nativeQuery = true)
    List<Adresse> findByCodePostalNative(@Param("codePostal") String codePostal);

    long countByVille(String ville);
    @Query("SELECT COUNT(a) FROM Adresse a WHERE a.ville = :ville")
    long countByVilleJPQL(@Param("ville") String ville);
    @Query(value = "SELECT COUNT(*) FROM adresse WHERE ville = :ville", nativeQuery = true)
    long countByVilleNative(@Param("ville") String ville);

    @Modifying
    @Transactional
    void deleteByVille(String ville);
    @Modifying
    @Transactional
    @Query("DELETE FROM Adresse a WHERE a.ville = :ville")
    void deleteByVilleJPQL(@Param("ville") String ville);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM adresse WHERE ville = :ville", nativeQuery = true)
    void deleteByVilleNative(@Param("ville") String ville);

    List<Adresse> findByVilleAndCodePostal(String ville, String codePostal);
    @Query("SELECT a FROM Adresse a WHERE a.ville = :ville AND a.codePostal = :codePostal")
    List<Adresse> findByVilleAndCodePostalJPQL(@Param("ville") String ville, @Param("codePostal") String codePostal);
    @Query(value = "SELECT * FROM adresse WHERE ville = :ville AND code_postal = :codePostal", nativeQuery = true)
    List<Adresse> findByVilleAndCodePostalNative(@Param("ville") String ville, @Param("codePostal") String codePostal);

    List<Adresse> findByVilleAndRueContainingIgnoreCase(String ville, String rue);
    @Query("SELECT a FROM Adresse a WHERE a.ville = :ville AND LOWER(a.rue) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Adresse> findByVilleAndRueContainingJPQL(@Param("ville") String ville, @Param("keyword") String keyword);
    @Query(value = "SELECT * FROM adresse WHERE ville = :ville AND LOWER(rue) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Adresse> findByVilleAndRueContainingNative(@Param("ville") String ville, @Param("keyword") String keyword);

    List<Adresse> findByVilleIn(List<String> villes);
    @Query("SELECT a FROM Adresse a WHERE a.ville IN :villes")
    List<Adresse> findByVilleInJPQL(@Param("villes") List<String> villes);
    @Query(value = "SELECT * FROM adresse WHERE ville IN :villes", nativeQuery = true)
    List<Adresse> findByVilleInNative(@Param("villes") List<String> villes);

    List<Adresse> findByCodePostalBetween(String minCodePostal, String maxCodePostal);
    @Query("SELECT a FROM Adresse a WHERE a.codePostal BETWEEN :minCodePostal AND :maxCodePostal")
    List<Adresse> findByCodePostalBetweenJPQL(@Param("minCodePostal") String minCodePostal, @Param("maxCodePostal") String maxCodePostal);
    @Query(value = "SELECT * FROM adresse WHERE code_postal BETWEEN :minCodePostal AND :maxCodePostal", nativeQuery = true)
    List<Adresse> findByCodePostalBetweenNative(@Param("minCodePostal") String minCodePostal, @Param("maxCodePostal") String maxCodePostal);

    List<Adresse> findByCodePostalGreaterThan(String codePostal);
    @Query("SELECT a FROM Adresse a WHERE a.codePostal > :codePostal")
    List<Adresse> findByCodePostalGreaterThanJPQL(@Param("codePostal") String codePostal);
    @Query(value = "SELECT * FROM adresse WHERE code_postal > :codePostal", nativeQuery = true)
    List<Adresse> findByCodePostalGreaterThanNative(@Param("codePostal") String codePostal);

    List<Adresse> findByCodePostalGreaterThanEqual(String codePostal);
    @Query("SELECT a FROM Adresse a WHERE a.codePostal >= :codePostal")
    List<Adresse> findByCodePostalGreaterThanEqualJPQL(@Param("codePostal") String codePostal);
    @Query(value = "SELECT * FROM adresse WHERE code_postal >= :codePostal", nativeQuery = true)
    List<Adresse> findByCodePostalGreaterThanEqualNative(@Param("codePostal") String codePostal);

    List<Adresse> findByCodePostalLessThan(String codePostal);
    @Query("SELECT a FROM Adresse a WHERE a.codePostal < :codePostal")
    List<Adresse> findByCodePostalLessThanJPQL(@Param("codePostal") String codePostal);
    @Query(value = "SELECT * FROM adresse WHERE code_postal < :codePostal", nativeQuery = true)
    List<Adresse> findByCodePostalLessThanNative(@Param("codePostal") String codePostal);

    List<Adresse> findByCodePostalLessThanEqual(String codePostal);
    @Query("SELECT a FROM Adresse a WHERE a.codePostal <= :codePostal")
    List<Adresse> findByCodePostalLessThanEqualJPQL(@Param("codePostal") String codePostal);
    @Query(value = "SELECT * FROM adresse WHERE code_postal <= :codePostal", nativeQuery = true)
    List<Adresse> findByCodePostalLessThanEqualNative(@Param("codePostal") String codePostal);

    List<Adresse> findByVilleAndRueStartingWithIgnoreCaseOrderByCodePostalAsc(String ville, String prefix);
    @Query("SELECT a FROM Adresse a WHERE a.ville = :ville AND LOWER(a.rue) LIKE LOWER(CONCAT(:prefix, '%')) ORDER BY a.codePostal ASC")
    List<Adresse> findByVilleAndRueStartingWithOrderByCodePostalJPQL(@Param("ville") String ville, @Param("prefix") String prefix);
    @Query(value = "SELECT * FROM adresse WHERE ville = :ville AND LOWER(rue) LIKE LOWER(CONCAT(:prefix, '%')) ORDER BY code_postal ASC", nativeQuery = true)
    List<Adresse> findByVilleAndRueStartingWithOrderByCodePostalNative(@Param("ville") String ville, @Param("prefix") String prefix);

    List<Adresse> findByRueStartingWithIgnoreCase(String prefix);
    @Query("SELECT a FROM Adresse a WHERE LOWER(a.rue) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Adresse> findByRueStartingWithJPQL(@Param("prefix") String prefix);
    @Query(value = "SELECT * FROM adresse WHERE LOWER(rue) LIKE LOWER(CONCAT(:prefix, '%'))", nativeQuery = true)
    List<Adresse> findByRueStartingWithNative(@Param("prefix") String prefix);

    List<Adresse> findByVilleEndingWithIgnoreCase(String suffix);
    @Query("SELECT a FROM Adresse a WHERE LOWER(a.ville) LIKE LOWER(CONCAT('%', :suffix))")
    List<Adresse> findByVilleEndingWithJPQL(@Param("suffix") String suffix);
    @Query(value = "SELECT * FROM adresse WHERE LOWER(ville) LIKE LOWER(CONCAT('%', :suffix))", nativeQuery = true)
    List<Adresse> findByVilleEndingWithNative(@Param("suffix") String suffix);

    List<Adresse> findByRueIsNull();
    @Query("SELECT a FROM Adresse a WHERE a.rue IS NULL")
    List<Adresse> findByRueIsNullJPQL();
    @Query(value = "SELECT * FROM adresse WHERE rue IS NULL", nativeQuery = true)
    List<Adresse> findByRueIsNullNative();

    List<Adresse> findByVilleIsNotNull();
    @Query("SELECT a FROM Adresse a WHERE a.ville IS NOT NULL")
    List<Adresse> findByVilleIsNotNullJPQL();
    @Query(value = "SELECT * FROM adresse WHERE ville IS NOT NULL", nativeQuery = true)
    List<Adresse> findByVilleIsNotNullNative();


    List<Adresse> findByVilleContainingIgnoreCase(String ville);

    List<Adresse> findByVilleStartingWithIgnoreCase(String prefix);

    @Query("SELECT a FROM Adresse a WHERE a.ville NOT LIKE %:keyword%")
    List<Adresse> findByVilleNotContaining(@Param("keyword") String keyword);

    @Query("SELECT a FROM Adresse a WHERE a.ville NOT LIKE :prefix%")
    List<Adresse> findByVilleNotStartingWith(@Param("prefix") String prefix);

    @Query("SELECT a FROM Adresse a WHERE a.codePostal <> :codePostal")
    List<Adresse> findByCodePostalNot(@Param("codePostal") String codePostal);

    List<Adresse> findByRue(String rue);

    List<Adresse> findByRueContainingIgnoreCase(String rue);

    List<Adresse> findByRueEndingWithIgnoreCase(String suffix);

    @Query("SELECT a FROM Adresse a WHERE LOWER(a.rue) NOT LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Adresse> findByRueNotContaining(@Param("keyword") String keyword);

}
