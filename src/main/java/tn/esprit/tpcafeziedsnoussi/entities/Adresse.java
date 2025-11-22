package tn.esprit.tpcafeziedsnoussi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "adresse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adresse")
    Long idAdresse;

    @Column(name = "rue")
    @NotBlank(message = "Street is required")
    @Size(min = 3, max = 100, message = "Street must be between 3 and 100 characters")
    String rue;

    @Column(name = "ville")
    @NotBlank(message = "City is required")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
    String ville;

    @Column(name = "code_postal")
    @NotBlank(message = "Postal code is required")
    @Pattern(regexp = "^\\d{4}$", message = "Postal code must be 4 digits")
    String codePostal;

    @OneToOne(mappedBy = "adresse")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    Client client;
}