package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Figyelmeztetes")

public class Figyelmeztetes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tanusitvany_id", nullable = false)
    private Tanusitvanyok tanusitvany_id;

    @Column (name = "figyelmeztetes_tipusa", nullable = false)
    private String figyelmeztetesTipus;

    @Column (name = "figyelmeztetes_ideje", nullable = false)
    private LocalDate figyelmeztetesIdeje;

    @Column(name = "uzenet", nullable = false)
    private String uzenet;

    @Column (name = "figyelmeztes_statusza", nullable = false)
    private Boolean figyelmeztetesStatusz;

    public Figyelmeztetes(Long id, Tanusitvanyok tanusitvany_id, String figyelmeztetesTipus, LocalDate figyelmeztetesIdeje,
                          String uzenet, Boolean figyelmeztetesStatusz) {
        this.id = id;
        this.tanusitvany_id = tanusitvany_id;
        this.figyelmeztetesTipus = figyelmeztetesTipus;
        this.figyelmeztetesIdeje = figyelmeztetesIdeje;
        this.uzenet = uzenet;
        this.figyelmeztetesStatusz = figyelmeztetesStatusz;
    }
}
