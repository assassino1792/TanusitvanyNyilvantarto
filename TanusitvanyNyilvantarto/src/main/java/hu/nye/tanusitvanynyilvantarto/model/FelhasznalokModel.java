package hu.nye.tanusitvanynyilvantarto.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FelhasznalokModel {
    private Long id;
    private String felhasznalonev;
    private String vezeteknev;
    private String keresztnev;
    private Timestamp letrehozva;
    private LocalDate modositva;

}
