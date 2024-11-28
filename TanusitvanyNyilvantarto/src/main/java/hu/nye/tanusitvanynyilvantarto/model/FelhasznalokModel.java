package hu.nye.tanusitvanynyilvantarto.model;


import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FelhasznalokModel {
    private Long id;
    private String felhasznalonev;
    private String vezeteknev;
    private String keresztnev;
    private LocalDateTime letrehozva;
}
