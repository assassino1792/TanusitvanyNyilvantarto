package hu.nye.tanusitvanynyilvantarto.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Start123"; // Beírt jelszó
        String encodedPassword = "$2a$10$OzdgwJ.qMNprerpcQdh6..brR09PFUTeMcPWz7n8rLvNGUkfkY2dS"; // DB-ben lévő hash

        boolean matches = encoder.matches(rawPassword, encodedPassword);
        System.out.println("Password matches: " + matches); // true, ha helyes
    }
}

