package hu.nye.tanusitvanynyilvantarto.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JelszoTesztelo {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String Jelszo = "Start123"; // Beírt jelszó
        String kodoltJelszo = "$2a$10$OzdgwJ.qMNprerpcQdh6..brR09PFUTeMcPWz7n8rLvNGUkfkY2dS"; // DB-ben lévő hash

        boolean hasonlit = encoder.matches(Jelszo, kodoltJelszo);
        System.out.println("Jelszó egyezik: " + hasonlit); // true, ha helyes
    }
}

