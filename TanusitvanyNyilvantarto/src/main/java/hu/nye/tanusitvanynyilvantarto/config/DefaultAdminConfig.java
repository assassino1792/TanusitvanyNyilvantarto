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
        if (felhasznalokRepository.count() == 0) { // Csak ha üres az adatbázis
            // Admin jogosultság ellenőrzése vagy létrehozása
            Jogosultsag adminJogosultsag = jogosultsagRepository.findByAuthority(Szerepkor.ADMIN)
                    .orElseGet(() -> {
                        Jogosultsag newJogosultsag = new Jogosultsag();
                        newJogosultsag.setAuthority(Szerepkor.ADMIN); // Enum használata
                        return jogosultsagRepository.save(newJogosultsag);
                    });

            // Default admin létrehozása
            Felhasznalok admin = new Felhasznalok();
            admin.setFelhasznaloNev("admin");
            admin.setJelszo(passwordEncoder.encode("admin123"));
            admin.setJogosultsagok(Collections.singleton(adminJogosultsag)); // Kapcsolat a szerepkörrel
            felhasznalokRepository.save(admin);

            System.out.println("Default admin user created: username='admin', password='admin123'");
        }
    }
}


