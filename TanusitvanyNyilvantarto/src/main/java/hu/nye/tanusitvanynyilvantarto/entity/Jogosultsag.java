package hu.nye.tanusitvanynyilvantarto.entity;

import hu.nye.tanusitvanynyilvantarto.model.Szerepkor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Jogosultsag")
public class Jogosultsag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Az Enum érték szövegként kerül az adatbázisba
    @Column(name = "szerepkor", nullable = false)
    private Szerepkor authority; // Enum típusú mező

    @ManyToOne
    @JoinColumn(name = "felhasznalo_id", nullable = false)
    private Felhasznalok felhasznalo;

}
