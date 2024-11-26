package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @Column (name="felhasznalonev", nullable = false)
    private String felhasznaloNev;

    @Column (name = "letrehozva", updatable = false)
    private LocalDate letrehozva;

    @Column (name = "modositva")
    private LocalDate modositva;

    public Felhasznalok(Long id, String felhasznaloNev, LocalDate letrehozva, LocalDate modositva) {
        this.id = id;
        this.felhasznaloNev = felhasznaloNev;
        this.letrehozva = letrehozva;
        this.modositva = modositva;
    }
}
