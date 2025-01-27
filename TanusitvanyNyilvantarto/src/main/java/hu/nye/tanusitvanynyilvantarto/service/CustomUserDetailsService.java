package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.repository.FelhasznalokRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import hu.nye.tanusitvanynyilvantarto.entity.Jogosultsag;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final FelhasznalokRepository felhasznalokRepository;

    public CustomUserDetailsService(FelhasznalokRepository felhasznalokRepository) {
        this.felhasznalokRepository = felhasznalokRepository;
    }
    //https://stackoverflow.com/questions/59417122/how-to-handle-usernamenotfoundexception-spring-security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Felhasznalok felhasznalo = felhasznalokRepository.findByFelhasznaloNev(username)
                .orElseThrow(() -> new UsernameNotFoundException("Felhasználó nem található: " + username));

        // Szerepkörök (jogosultságok) kinyerése
        Set<String> roles = felhasznalo.getJogosultsagok().stream()
                .map(Jogosultsag::getAuthority) // Jogosultság osztályból visszadja, hogy user vagy admin
                .map(Enum::name) // Az Enum értékeket szöveggé alakítjuk
                .collect(Collectors.toSet());

        return User.builder()
                .username(felhasznalo.getFelhasznaloNev())
                .password(felhasznalo.getJelszo()) // Titkosított jelszó
                .roles(roles.toArray(new String[0])) // Szerepkörök átadása
                .build();
    }
}
