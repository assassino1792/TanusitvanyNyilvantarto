package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import hu.nye.tanusitvanynyilvantarto.model.UzenetTipus;
import hu.nye.tanusitvanynyilvantarto.repository.TanusitvanyokRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RiasztasService {

    private final TanusitvanyokRepository tanusitvanyokRepository;

    public RiasztasService(TanusitvanyokRepository tanusitvanyokRepository) {
        this.tanusitvanyokRepository = tanusitvanyokRepository;
    }


    public List<Tanusitvanyok> getAllTanusitvanyok() {
        return tanusitvanyokRepository.findAll();
    }
    public List<Tanusitvanyok> getAktivTanusitvanyok() {
        return tanusitvanyokRepository.findByStatusz("Aktív");
    }

    /**
     * Riasztás típusának kiszámítása a tanúsítvány lejárati ideje alapján.
     */


    public UzenetTipus szamoljRiasztasTipust(Tanusitvanyok tanusitvany) {
        LocalDate lejaratiIdo = tanusitvany.getLejaratiIdo();
        long daysToExpiry = ChronoUnit.DAYS.between(LocalDate.now(), lejaratiIdo);

        if (daysToExpiry <= 0) {
            return UzenetTipus.EXPIRED;
        } else if (daysToExpiry <= 3) {
            return UzenetTipus.CRITICAL;
        } else if (daysToExpiry <= 14) {
            return UzenetTipus.WARNING;
        }
        return null; // Nem kell riasztás
    }

    /**
     * Időzített riasztás ellenőrzés. Minden nap egyszer fut.
     */
    /*@Scheduled(cron = "0 0 0 * * *") // Minden nap éjfélkor */
    @Scheduled(cron = "0 */2 * * * *") // 2 percenként
    public void ellenorizdRiasztasokat() {
        System.out.println("Riasztások ellenőrzése elkezdődött...");

        List<Tanusitvanyok> tanusitvanyok = getAllTanusitvanyok();

        for (Tanusitvanyok tanusitvany : tanusitvanyok) {
            UzenetTipus riasztasTipus = szamoljRiasztasTipust(tanusitvany);

            if (riasztasTipus == UzenetTipus.EXPIRED) {
                System.out.printf("Lejárt tanúsítvány státuszának frissítése: %s%n", tanusitvany.getSzerverNev());
                tanusitvany.setStatusz("Inaktív"); // Státusz módosítása
                tanusitvanyokRepository.save(tanusitvany); // Mentés az adatbázisba
                tanusitvanyokRepository.flush();
            } else if (riasztasTipus != null) {
                System.out.printf("Riasztás típusa: %s | Tanúsítvány: %s%n",
                        riasztasTipus, tanusitvany.getSzerverNev());
            }
        }
        System.out.println("Riasztások ellenőrzése befejeződött!");
    }

    public boolean shouldBlink() {
        return tanusitvanyokRepository.findByStatusz("Aktív").stream()
                .anyMatch(tanusitvany -> {
                    UzenetTipus tipus = szamoljRiasztasTipust(tanusitvany);
                    return tipus == UzenetTipus.CRITICAL || tipus == UzenetTipus.WARNING;
                });
    }

}


