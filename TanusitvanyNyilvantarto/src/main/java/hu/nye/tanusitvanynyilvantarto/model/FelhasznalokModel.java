package hu.nye.tanusitvanynyilvantarto.model;


import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FelhasznalokModel {
    private Long id;

    @NotNull
    @Size(min = 3,max = 30, message = "A felhasználónév nem lehet hosszabb, mint 30 karakter.")
    private String felhasznalonev;

    @NotNull
    @Size(min = 3, max = 30, message = "A vezetéknév nem lehet hosszabb, mint 30 karakter.")
    private String vezeteknev;

    @NotNull
    @Size(min = 3, max = 30, message = "A keresztnév nem lehet hosszabb, mint 30 karakter.")
    private String keresztnev;

    @Email(message = "Az email cím nem érvényes.")
    @Size(max = 30, message = "Az email nem lehet hosszabb, mint 30 karakter.")
    private String email;

    @NotNull
    @Size(min = 6, max = 30, message = "A jelszónak legalább 6 és legfeljebb 30 karakter hosszúnak kell lennie.")
    private String jelszo;
    private LocalDateTime letrehozva;
    }
