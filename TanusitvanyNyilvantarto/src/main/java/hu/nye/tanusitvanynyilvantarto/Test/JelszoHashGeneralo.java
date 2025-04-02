package hu.nye.tanusitvanynyilvantarto.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JelszoHashGeneralo {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String Jelszo = "admin123";
        String kodoltJelszo = encoder.encode(Jelszo);
        System.out.println("Titkosított jelszó: " + kodoltJelszo);
    }
}

