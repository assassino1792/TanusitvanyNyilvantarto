package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Felhasznalok")
public class Felhasznalok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "felhasznalonev", nullable = false)
    private String felhasznaloNev;

    @Column(name = "vezeteknev", nullable = false)
    private String vezetekNev;

    @Column(name = "keresztnev", nullable = false)
    private String keresztNev;

    // Adatbázisba mentett natív LocalDateTime
    @Column(name = "letrehozva", updatable = false, nullable = false)
    private LocalDateTime letrehozva;

    // Nem mentjük az adatbázisba, csak formázáshoz használjuk
    @Transient
    private String formazottDatum;

    public Felhasznalok(Long id, String felhasznaloNev, String vezetekNev, String keresztNev, LocalDateTime letrehozva, String formazottDatum) {
        this.id = id;
        this.felhasznaloNev = felhasznaloNev;
        this.vezetekNev = vezetekNev;
        this.keresztNev = keresztNev;
        this.letrehozva = letrehozva;
        this.formazottDatum = formazottDatum;
    }

    // Automatikus beállítás mentés előtt
    @PrePersist
    public void onCreate() {
        this.letrehozva = LocalDateTime.now();
    }

    // Formázott dátum getter
    public String getFormazottDatum() {
        if (this.letrehozva != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
            return this.letrehozva.format(formatter); // Dátum formázása
        }
        return null;
    }
}
