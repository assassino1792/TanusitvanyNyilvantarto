package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Tanusitvanyok")

public class Tanusitvanyok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "szerver_neve", nullable = false)
    private String szerverNev;

    @Column (name = "tanusitvany_tipusa", nullable = false)
    private String tanusitvanyTipus;

    @Column (name = "kezdeti_ido", nullable = false)
    private LocalDate kezdetiIdo;

    @Column (name = "lejarati_ido", nullable = false)
    private LocalDate lejaratiIdo;

    @Column (name = "statusz")
    private String statusz;

    @Column (name = "kiallito_neve")
    private String kiallitoNeve;

    @Column (name = "letrehozva", updatable = false)
    private LocalDate letrehozva;

    @Column (name = "modositva")
    private LocalDate modositva;

    public Tanusitvanyok(Long id, String szerverNev, String tanusitvanyTipus, LocalDate kezdetiIdo, LocalDate lejaratiIdo,
                         String statusz, String kiallitoNeve, LocalDate letrehozva, LocalDate modositva) {
        this.id = id;
        this.szerverNev = szerverNev;
        this.tanusitvanyTipus = tanusitvanyTipus;
        this.kezdetiIdo = kezdetiIdo;
        this.lejaratiIdo = lejaratiIdo;
        this.statusz = statusz;
        this.kiallitoNeve = kiallitoNeve;
        this.letrehozva = letrehozva;
        this.modositva = modositva;
    }
}
