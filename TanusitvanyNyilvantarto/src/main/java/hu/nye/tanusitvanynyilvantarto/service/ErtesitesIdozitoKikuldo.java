package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import hu.nye.tanusitvanynyilvantarto.model.UzenetTipus;
import hu.nye.tanusitvanynyilvantarto.repository.TanusitvanyokRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ErtesitesIdozitoKikuldo {

    private final TanusitvanyokRepository tanusitvanyokRepository;
    private final ErtesitesService ertesitesService;
    private final EmailService emailService;

    public ErtesitesIdozitoKikuldo(TanusitvanyokRepository tanusitvanyokRepository,
                                   ErtesitesService ertesitesService,
                                   EmailService emailService) {
        this.tanusitvanyokRepository = tanusitvanyokRepository;
        this.ertesitesService = ertesitesService;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 8 * * *")
    //@Scheduled(cron = "0 */2 * * * *") // Kétpercenként fut
    public void ellenorizdEsKuldjErtesitest() {
        List<Tanusitvanyok> aktivTanusitvanyok = tanusitvanyokRepository.findByStatusz("Aktív");

        for (Tanusitvanyok tanusitvany : aktivTanusitvanyok) {
            UzenetTipus tipus = RiasztasTipus(tanusitvany);

            if (tipus == UzenetTipus.Lejárt && !tanusitvany.isExpiredEmailSent()) {
                // Csak akkor küldünk e-mailt, ha még nem küldtük el
                String email = ertesitesService.getErtesitesiEmail(tanusitvany);
                String tartalom = ertesitesService.getErtesitesTartalom(tanusitvany, tipus);

                emailService.sendMail(email, "Riasztás a tanúsítványról", tartalom);

                // Az e-mail elküldésre került
                tanusitvany.setExpiredEmailSent(true);
                tanusitvanyokRepository.save(tanusitvany);

                System.out.printf("Értesítés elküldve az EXPIRED állapotról: %s -> %s%n", email, tipus);
            } else if (tipus == UzenetTipus.Kritikus || tipus == UzenetTipus.Figyelmeztetés) {
                String email = ertesitesService.getErtesitesiEmail(tanusitvany);
                String tartalom = ertesitesService.getErtesitesTartalom(tanusitvany, tipus);

                emailService.sendMail(email, "Riasztás a tanúsítványról", tartalom);

                System.out.printf("Értesítés kiküldve: %s -> %s%n", email, tipus);
            }
        }
    }

    private UzenetTipus RiasztasTipus(Tanusitvanyok tanusitvany) {
        long daysToExpiry = ChronoUnit.DAYS.between(
                java.time.LocalDate.now(),
                tanusitvany.getLejaratiIdo()
        );

        if (daysToExpiry <= 0) {
            return UzenetTipus.Lejárt;
        } else if (daysToExpiry <= 3) {
            return UzenetTipus.Kritikus;
        } else if (daysToExpiry <= 14) {
            return UzenetTipus.Figyelmeztetés;
        }
        return null;
    }
}
