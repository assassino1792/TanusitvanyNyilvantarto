package hu.nye.tanusitvanynyilvantarto.config;


import hu.nye.tanusitvanynyilvantarto.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder,CustomUserDetailsService customUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }
    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("admin")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
    */

    //https://stackoverflow.com/questions/77504542/rewriting-a-spring-security-deprecated-authenticationmanager-httpsecurity
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService) // CustomUserDetailsService használata
                .passwordEncoder(passwordEncoder) // Jelszó titkosítása
                .and()
                .build();
    }

    //https://stackoverflow.com/questions/79370047/spring-security-form-dont-want-to-login
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/bejelentkezes", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/felhasznalok").hasAnyRole("USER","ADMIN") // olvasás jog
                        .requestMatchers("/felhasznalok/add", "felhasznalok/edit/**","felhasznalok/delete/**","felhasznalok/updatepw/**").hasAnyRole("ADMIN") // írás
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/bejelentkezes") // Saját bejelentkezési oldal
                        .loginProcessingUrl("/do-login") // A hitelesítés feldolgozásának URL-je
                        .defaultSuccessUrl("/kezdolap", true) // Sikeres bejelentkezés
                        .failureUrl("/bejelentkezes?error=true") // Hibás hitelesítés
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/bejelentkezes?logout=true") // Kijelentkezés
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    request.getSession().setAttribute("errorMessage", "Nincs jogosultságod a művelet végrehajtásához!");
                    response.sendRedirect("/felhasznalok");
                })
        );

        return http.build();
    }



}
