package hu.nye.tanusitvanynyilvantarto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
@Table (name = "Uzenet_tipusok")
public class UzenetTipusok {

    public UzenetTipusok() {
        this.riasztasWarningUzenet = "A tanúsítvány hamarosan le fog járni.";
        this.riasztasCriticalUzenet = "A tanúsítvány lejárati ideje már kritikus.";
        this.riasztasExpiredUzenet = "A tanúsítvány lejárt és inaktív a státusza.";
    }

    @Id
    private Long id;

    @Column(name = "riasztas_warning_uzenet")
    private String riasztasWarningUzenet;

    @Column(name = "riasztas_critical_uzenet")
    private String riasztasCriticalUzenet;

    @Column(name = "riasztas_expired_uzenet")
    private String riasztasExpiredUzenet;

    public UzenetTipusok(String riasztasWarningUzenet, String riasztasCriticalUzenet, String riasztasExpiredUzenet) {
        this.riasztasWarningUzenet = riasztasWarningUzenet;
        this.riasztasCriticalUzenet = riasztasCriticalUzenet;
        this.riasztasExpiredUzenet = riasztasExpiredUzenet;
    }
}
