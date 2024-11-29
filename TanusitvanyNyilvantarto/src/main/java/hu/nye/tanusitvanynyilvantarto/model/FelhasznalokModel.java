package hu.nye.tanusitvanynyilvantarto.model;


import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String jelszo;
    private LocalDateTime letrehozva;
    }
