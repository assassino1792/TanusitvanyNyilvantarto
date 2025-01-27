package hu.nye.tanusitvanynyilvantarto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("PasswordEncoder Bean létrejött az EncoderConfig osztályban.");
        return new BCryptPasswordEncoder();
    }
}
