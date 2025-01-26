package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import hu.nye.tanusitvanynyilvantarto.model.UzenetTipus;
import hu.nye.tanusitvanynyilvantarto.repository.TanusitvanyokRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class RiasztasService {

    private static final Logger logger = LoggerFactory.getLogger(RiasztasService.class);

    private final TanusitvanyokRepository tanusitvanyokRepository;

    // E-mail küldési nyomkövetés a már lejárt tanúsítványokhoz
    private final Set<Long> alreadyExpired = new HashSet<>();

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
            return UzenetTipus.Lejárt;
        } else if (daysToExpiry <= 3) {
            return UzenetTipus.Kritikus;
        } else if (daysToExpiry <= 14) {
            return UzenetTipus.Figyelmeztetés;
        }
        return null; // Nem kell riasztás
    }

    /**
     * Időzített riasztás ellenőrzés. Minden nap egyszer fut.
     */
    //@Scheduled(cron = "0 0 0 * * *") // Minden nap éjfélkor */
    @Scheduled(cron = "0 */2 * * * *") // 2 percenként
    public void ellenorizdRiasztasokat() {
        System.out.println("Riasztások ellenőrzése elkezdődött...");

        List<Tanusitvanyok> tanusitvanyok = getAllTanusitvanyok();

        for (Tanusitvanyok tanusitvany : tanusitvanyok) {
            UzenetTipus riasztasTipus = szamoljRiasztasTipust(tanusitvany);

            if (riasztasTipus == UzenetTipus.Lejárt) {
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
                    return tipus == UzenetTipus.Kritikus || tipus == UzenetTipus.Figyelmeztetés;
                });
    }
    public Map<String, Long> KritikusWarningSzamlalo() {
        System.out.println("KritikusWarningSzamlalo() metódus meghívva!");
        // Csak az aktív tanúsítványok lekérdezése
        List<Tanusitvanyok> tanusitvanyok = getAktivTanusitvanyok();
        System.out.println("Aktív tanúsítványok száma: " + tanusitvanyok.size());

        Map<String, Long> Szamlalo = new HashMap<>();

        // Szűrés és számolás a tanúsítványok között
        long criticalSzamlalo = tanusitvanyok.stream()
                .filter(t -> szamoljRiasztasTipust(t) == UzenetTipus.Kritikus)
                .count();
        long warningSzamlalo = tanusitvanyok.stream()
                .filter(t -> szamoljRiasztasTipust(t) == UzenetTipus.Figyelmeztetés)
                .count();

        logger.info("Aktív Critical riasztások száma: {}", criticalSzamlalo);
        logger.info("Aktív Warning riasztások száma: {}", warningSzamlalo);

        // Eredmények hozzáadása a Map-hez
        Szamlalo.put("critical", criticalSzamlalo);
        Szamlalo.put("warning", warningSzamlalo);

        return Szamlalo;
    }


}


