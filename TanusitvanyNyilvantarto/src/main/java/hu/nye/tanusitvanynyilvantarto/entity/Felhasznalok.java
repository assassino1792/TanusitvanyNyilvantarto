package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
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

    @Column (name="vezeteknev", nullable = false)
    private String vezetekNev;

    @Column (name="keresztnev", nullable = false)
    private String keresztNev;

    @Column (name = "letrehozva", updatable = false)
    private Timestamp letrehozva;

    @Column (name = "modositva")
    private LocalDate modositva;

    public Felhasznalok(Long id, String felhasznaloNev, String vezetekNev, String keresztNev,
                        Timestamp letrehozva, LocalDate modositva) {
        this.id = id;
        this.felhasznaloNev = felhasznaloNev;
        this.vezetekNev = vezetekNev;
        this.keresztNev = keresztNev;
        this.letrehozva = letrehozva;
        this.modositva = modositva;
    }
/*
    @PrePersist
    protected void onCreate() {
        this.letrehozva = ;
    }

 */
    @PreUpdate
    protected void onUpdate() {
        this.modositva = LocalDate.now();
    }

}
