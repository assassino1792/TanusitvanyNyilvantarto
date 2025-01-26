package hu.nye.tanusitvanynyilvantarto.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123"; // Beírt jelszó
        String encodedPassword = "$2a$10$Dow1N5l8K1Q9eG1Z5z5eUO6Q9eG1Z5z5eUO6Q9eG1Z5z5eUO6Q9eG"; // DB-ben lévő hash

        boolean matches = encoder.matches(rawPassword, encodedPassword);
        System.out.println("Password matches: " + matches); // true, ha helyes
    }
}

