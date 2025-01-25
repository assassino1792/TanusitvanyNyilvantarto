package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Tanusitvanyok tanusitvany; // Név egyszerűsítése, `_id` redundáns

    @Transient // Nem perzisztálódik, mindig dinamikusan számoljuk
    private String riasztasTipus;

    @Transient // Nem perzisztálódik, mindig dinamikusan számoljuk
    private boolean riasztasAktiv;

    /**
     * Dinamikusan számolja ki a riasztás típusát a tanúsítvány lejárati ideje alapján.
     */
    public String getRiasztasTipus() {
        if (tanusitvany == null || tanusitvany.getLejaratiIdo() == null) {
            return "UNKNOWN";
        }
        long daysToExpiry = java.time.temporal.ChronoUnit.DAYS.between(java.time.LocalDate.now(), tanusitvany.getLejaratiIdo());

        if (daysToExpiry < 0) {
            return "Lejárt";
        } else if (daysToExpiry <= 3) {
            return "Kritikus";
        } else if (daysToExpiry <= 14) {
            return "Figyelmeztetés";
        }
        return "OK";
    }

    /**
     * Ellenőrzi, hogy a riasztás még aktív-e (azaz a tanúsítvány nem járt le).
     */
    public boolean isRiasztasAktiv() {
        if (tanusitvany == null || tanusitvany.getLejaratiIdo() == null) {
            return false;
        }
        return java.time.LocalDate.now().isBefore(tanusitvany.getLejaratiIdo());
    }
}
