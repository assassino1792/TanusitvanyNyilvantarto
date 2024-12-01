package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTestController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/send-test-email")
    public String sendTestEmail(@RequestParam String to) {
        try {
            emailService.sendMail(to, "Teszt Email", "Ez egy teszt üzenet, hogy ellenőrizzük az SMTP konfigurációt.");
            return "Teszt email sikeresen elküldve: " + to;
        } catch (Exception e) {
            e.printStackTrace();
            return "Hiba történt az email küldésekor: " + e.getMessage();
        }
    }
}
