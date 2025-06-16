package org.example.weatherbackend.WeatherApp.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // ❌ Inaktiverar CSRF – säkert för REST API utan cookies
                .csrf(csrf -> csrf.disable())

                // 🔐 Definiera vilka requests som kräver autentisering
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/weather/all").permitAll()  // öppet för alla
                        .anyRequest().authenticated()                // alla andra kräver inloggning
                )

                // 🔐 Aktiverar HTTP Basic Authentication (ej deprecated)
                .httpBasic(withDefaults()); // detta är det moderna sättet

        return http.build();
    }
}
