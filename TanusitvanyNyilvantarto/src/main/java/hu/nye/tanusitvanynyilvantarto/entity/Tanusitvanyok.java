package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
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

    @Column (name = "reszletek")
    private String reszletek;

    @CreationTimestamp
    @Column(name = "letrehozva", updatable = false)
    private LocalDateTime letrehozva;


    public Tanusitvanyok(Long id, String szerverNev, String tanusitvanyTipus, LocalDate kezdetiIdo, LocalDate lejaratiIdo,
                         String statusz, String kiallitoNeve, String reszletek, LocalDateTime letrehozva) {
        this.id = id;
        this.szerverNev = szerverNev;
        this.tanusitvanyTipus = tanusitvanyTipus;
        this.kezdetiIdo = kezdetiIdo;
        this.lejaratiIdo = lejaratiIdo;
        this.statusz = statusz;
        this.kiallitoNeve = kiallitoNeve;
        this.reszletek = reszletek;
        this.letrehozva = letrehozva;
    }
}
