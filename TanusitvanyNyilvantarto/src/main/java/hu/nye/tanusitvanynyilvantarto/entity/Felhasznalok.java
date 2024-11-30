package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

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

    @NotNull
    @Size(min = 3,max = 30, message = "A felhasználónév nem lehet hosszabb, mint 30 karakter.")
    @Column(name = "felhasznalonev", unique = true,nullable = false)
    private String felhasznaloNev;

    @NotNull
    @Size(min = 3, max = 30, message = "A vezetéknév nem lehet hosszabb, mint 30 karakter.")
    @Column(name = "vezeteknev", nullable = false)
    private String vezetekNev;

    @NotNull
    @Size(min = 3, max = 30, message = "A keresztnév nem lehet hosszabb, mint 30 karakter.")
    @Column(name = "keresztnev", nullable = false)
    private String keresztNev;

    @Column(name = "email",unique = true)
    @Email(message = "Az email cím nem érvényes.")
    @Size(max = 30, message = "Az email nem lehet hosszabb, mint 30 karakter.")
    private String email;

    @NotNull
    @Size(min = 6, max = 30, message = "A jelszónak legalább 6 és legfeljebb 30 karakter hosszúnak kell lennie.")
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
