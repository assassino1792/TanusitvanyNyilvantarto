package hu.nye.tanusitvanynyilvantarto.config;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.entity.Jogosultsag;
import hu.nye.tanusitvanynyilvantarto.model.Szerepkor;
import hu.nye.tanusitvanynyilvantarto.repository.FelhasznalokRepository;
import hu.nye.tanusitvanynyilvantarto.repository.JogosultsagRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DefaultAdminConfig {
    private final FelhasznalokRepository felhasznalokRepository;
    private final JogosultsagRepository jogosultsagRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultAdminConfig(FelhasznalokRepository felhasznalokRepository,
                              JogosultsagRepository jogosultsagRepository,
                              PasswordEncoder passwordEncoder) {
        this.felhasznalokRepository = felhasznalokRepository;
        this.jogosultsagRepository = jogosultsagRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createDefaultAdmin() {
        if (!felhasznalokRepository.existsByFelhasznaloNev("admin")) {
            System.out.println("Admin user does not exist, creating admin...");

            // Default admin felhasználó létrehozása
            Felhasznalok admin = new Felhasznalok();
            admin.setFelhasznaloNev("admin");
            admin.setVezetekNev("Admin");
            admin.setKeresztNev("Admin");
            admin.setEmail("admin@example.com");
            admin.setJelszo(passwordEncoder.encode("admin123")); // Titkosított jelszó
            felhasznalokRepository.save(admin); // Előbb mentjük a felhasználót, hogy legyen ID-je

            // Admin jogosultság létrehozása
            Jogosultsag adminJogosultsag = new Jogosultsag();
            adminJogosultsag.setAuthority(Szerepkor.ADMIN);
            adminJogosultsag.setFelhasznalo(admin); // Beállítjuk a felhasználót
            jogosultsagRepository.save(adminJogosultsag); // Mentjük a jogosultságot

            System.out.println("Default admin user created: username='admin', password='admin123'");
        } else {
            System.out.println("Admin user already exists.");
        }
    }

}


