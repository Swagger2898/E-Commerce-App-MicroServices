package configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login","/register").permitAll()  // Allow /login without authentication
                        .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .formLogin(withDefaults()); // Default form login
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http){

        return 
    }
}