package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Riasztasok")
public class Riasztasok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tanusitvany_id", nullable = false)
    private Tanusitvanyok tanusitvany_id;

    @Column (name = "riasztas_tipusa", nullable = false)
    private String riasztasTipus;

    @Column (name = "riasztas_ideje", nullable = false)
    private LocalDate riasztasIdeje;


    @Column (name = "riasztas_statusza", nullable = false)
    private Boolean riasztasStatusz;

    public Riasztasok(Long id, Tanusitvanyok tanusitvany_id, String riasztasTipus, LocalDate riasztasIdeje,
                      String riasztasWarningUzenet, String riasztasCriticalUzenet, String riasztasExpiredUzenet,
                      Boolean riasztasStatusz) {
        this.id = id;
        this.tanusitvany_id = tanusitvany_id;
        this.riasztasTipus = riasztasTipus;
        this.riasztasIdeje = riasztasIdeje;
              this.riasztasStatusz = riasztasStatusz;
    }
}
