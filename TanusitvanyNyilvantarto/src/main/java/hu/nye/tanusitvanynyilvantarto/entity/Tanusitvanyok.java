package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Tanusitvanyok",
        indexes = @Index(name = "idx_lejarati_ido", columnList = "lejarati_ido")) // Index a gyorsabb keresésért
public class Tanusitvanyok {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "szerver_neve", nullable = false)
    private String szerverNev;

    @NotNull
    @Column(name = "tanusitvany_tipusa", nullable = false)
    private String tanusitvanyTipus;

    @NotNull
    @Column(name = "kezdeti_ido", nullable = false)
    private LocalDate kezdetiIdo;

    @NotNull
    @Column(name = "lejarati_ido", nullable = false)
    private LocalDate lejaratiIdo;

    @NotNull
    @Column(name = "statusz")
    private String statusz;

    @NotNull
    @Column(name = "kiallito_neve")
    private String kiallitoNeve;

    @Column(name = "reszletek")
    private String reszletek;

    @CreationTimestamp
    @Column(name = "letrehozva", updatable = false)
    private LocalDateTime letrehozva;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean expiredEmailSent = false;



    /**
     * Dinamikusan számolja a riasztási típust a lejárati idő alapján.
     */
    public String getRiasztasTipus() {
        LocalDate now = LocalDate.now();
        long daysToExpiry = ChronoUnit.DAYS.between(now, this.lejaratiIdo);

        if (daysToExpiry < 0) {
            return "EXPIRED";
        } else if (daysToExpiry <= 3) {
            return "CRITICAL";
        } else if (daysToExpiry <= 14) {
            return "WARNING";
        }
        return "OK";
    }

    /**
     * Ellenőrzi, hogy a tanúsítvány aktív-e (nem járt le).
     */
    public boolean isAktiv() {
        return "AKTÍV".equals(this.statusz) && LocalDate.now().isBefore(this.lejaratiIdo);
    }


    /**
     * Ellenőrzi, hogy a tanúsítvány kritikus állapotban van-e.
     */
    public boolean isCritical() {
        return "CRITICAL".equals(getRiasztasTipus());
    }

    /**
     * Ellenőrzi, hogy a tanúsítvány figyelmeztetési állapotban van-e.
     */
    public boolean isWarning() {
        return "WARNING".equals(getRiasztasTipus());
    }

    public Tanusitvanyok(Long id, String szerverNev, String tanusitvanyTipus, LocalDate kezdetiIdo, LocalDate lejaratiIdo,
                         String statusz, String kiallitoNeve, String reszletek, LocalDateTime letrehozva, boolean expiredEmailSent) {
        this.id = id;
        this.szerverNev = szerverNev;
        this.tanusitvanyTipus = tanusitvanyTipus;
        this.kezdetiIdo = kezdetiIdo;
        this.lejaratiIdo = lejaratiIdo;
        this.statusz = statusz;
        this.kiallitoNeve = kiallitoNeve;
        this.reszletek = reszletek;
        this.letrehozva = letrehozva;
        this.expiredEmailSent = expiredEmailSent;
    }
}
