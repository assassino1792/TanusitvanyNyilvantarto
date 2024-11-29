package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.Email;
import java.time.LocalDateTime;

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

    @Column(name = "felhasznalonev", unique = true,nullable = false)
    private String felhasznaloNev;

    @Column(name = "vezeteknev", nullable = false)
    private String vezetekNev;

    @Column(name = "keresztnev", nullable = false)
    private String keresztNev;

    @Column(name = "email",unique = true)
    @Email
    private String email;

    @Column(name ="jelszo", nullable = false)
    private String jelszo;

    @Column(name = "letrehozva", updatable = false)
    private LocalDateTime letrehozva;


    public Felhasznalok(Long id, String felhasznaloNev, String vezetekNev, String keresztNev, String email, String jelszo,
                        LocalDateTime letrehozva) {
        this.id = id;
        this.felhasznaloNev = felhasznaloNev;
        this.vezetekNev = vezetekNev;
        this.keresztNev = keresztNev;
        this.email = email;
        this.jelszo = jelszo;
        this.letrehozva = letrehozva;
    }

    @PrePersist
    public void onCreate() {
        this.letrehozva = LocalDateTime.now();
    }
}
