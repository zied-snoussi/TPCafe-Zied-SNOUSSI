package tn.esprit.tpcafeziedsnoussi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "carte_fidelite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarteFidelite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carte_fidelite")
    Long idCarteFidelite;

    @Column(name = "points_accumules")
    @PositiveOrZero(message = "Accumulated points must be zero or positive")
    int pointsAccumules;

    @Column(name = "date_creation")
    @NotNull(message = "Creation date is required")
    LocalDate dateCreation;

    @OneToOne(mappedBy = "carteFidelite")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    Client client;
}