package hu.nye.tanusitvanynyilvantarto.model;


import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;


import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FelhasznalokModel {
    private Long id;
    private String felhasznalonev;
    private String vezeteknev;
    private String keresztnev;
    private String email;
    @NotNull
    @Size(min = 6, max = 30, message = "A jelszónak legalább 6 és legfeljebb 30 karakter hosszúnak kell lennie.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_\\-])[A-Za-z\\d@$!%*?&_\\-]{6,}$",
            message = "A jelszónak tartalmaznia kell kisbetűt, nagybetűt, számot, speciális karaktert (pl. @, $, _, -, stb.)," +
                    " és legalább 6 karakter hosszúnak kell lennie."
    )
    private String jelszo;
    private LocalDateTime letrehozva;
    }
