package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import hu.nye.tanusitvanynyilvantarto.model.UzenetTipus;
import org.springframework.stereotype.Service;

@Service
public class ErtesitesService {

    public String getErtesitesiEmail(Tanusitvanyok tanusitvany) {

       return "emailtanusitvanywebapp@gmail.com";
    }

    public String getErtesitesTartalom(Tanusitvanyok tanusitvany, UzenetTipus tipus) {
        long daysToExpiry = java.time.temporal.ChronoUnit.DAYS.between(
                java.time.LocalDate.now(), tanusitvany.getLejaratiIdo()
        );

        String subject;
        String body;

        switch (tipus) {
            case Lejárt:
                subject = "Tanúsítvány lejárt!";
                body = String.format(
                        "Tisztelt Ügyfél!\n\nA(z) %s tanúsítvány lejárt.\nKérjük, frissítse a tanúsítványt.\n\nÜdvözlettel,\nRendszer",
                        tanusitvany.getSzerverNev()
                );
                break;
            case Kritikus:
                subject = "Tanúsítvány kritikus állapotban!";
                body = String.format(
                        "Tisztelt Ügyfél!\n\nA(z) %s tanúsítvány kritikus állapotban van.\nHátralévő idő: %d nap.\n\nKérjük, mielőbb frissítse a tanúsítványt.\n\nÜdvözlettel,\nRendszer",
                        tanusitvany.getSzerverNev(), daysToExpiry
                );
                break;
            case Figyelmeztetés:
                subject = "Tanúsítvány figyelmeztetés!";
                body = String.format(
                        "Tisztelt Ügyfél!\n\nA(z) %s tanúsítvány %d nap múlva lejár.\nKérjük, frissítse a tanúsítványt időben.\n\nÜdvözlettel,\nRendszer",
                        tanusitvany.getSzerverNev(), daysToExpiry
                );
                break;
            default:
                subject = "Tanúsítvány állapot frissítés";
                body = "Ismeretlen riasztási típus.";
        }

        return String.format("Subject: %s\n\n%s", subject, body);
    }
}
