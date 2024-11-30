package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Tanusitvanyok")

public class Tanusitvanyok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "szerver_neve", nullable = false)
    private String szerverNev;

    @NotNull
    @Column (name = "tanusitvany_tipusa", nullable = false)
    private String tanusitvanyTipus;

    @NotNull
    @Column (name = "kezdeti_ido", nullable = false)
    private LocalDate kezdetiIdo;

    @NotNull
    @Column (name = "lejarati_ido", nullable = false)
    private LocalDate lejaratiIdo;

    @NotNull
    @Column (name = "statusz")
    private String statusz;

    @NotNull
    @Column (name = "kiallito_neve")
    private String kiallitoNeve;

    @Column (name = "letrehozva", updatable = false)
    private LocalDateTime letrehozva;

    @Column (name = "modositva")
    private LocalDateTime modositva;

    public Tanusitvanyok(Long id, String szerverNev, String tanusitvanyTipus, LocalDate kezdetiIdo, LocalDate lejaratiIdo,
                         String statusz, String kiallitoNeve, LocalDateTime letrehozva, LocalDateTime modositva) {
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
