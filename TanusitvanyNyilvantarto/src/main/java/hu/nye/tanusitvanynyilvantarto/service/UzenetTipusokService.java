package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.UzenetTipusok;
import hu.nye.tanusitvanynyilvantarto.repository.UzenetTipusokRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UzenetTipusokService implements CommandLineRunner {
    private final UzenetTipusokRepository uzenetTipusokRepository;

    public UzenetTipusokService (UzenetTipusokRepository uzenetTipusokRepository) {
        this.uzenetTipusokRepository = uzenetTipusokRepository;
    }

    @Override
    public void run(String... args) {
        if (!uzenetTipusokRepository.existsById(1L)) {
            UzenetTipusok uzenetTipusok = new UzenetTipusok();
            uzenetTipusok.setId(1L);
            uzenetTipusok.setRiasztasWarningUzenet("A tanúsítvány hamarosan le fog járni.");
            uzenetTipusok.setRiasztasCriticalUzenet("A tanúsítvány lejárati ideje már kritikus.");
            uzenetTipusok.setRiasztasExpiredUzenet("A tanúsítvány lejárt és inaktív a státusza.");
            uzenetTipusokRepository.save(uzenetTipusok);
            System.out.println("UzenetTipusok mentésre kerültek az adatbázisban.");
        } else {
            System.out.println("UzenetTipusok már szerepelnek az adatbázisban.");
        }
    }
}
