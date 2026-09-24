package com.TrachAcademy.journalApp.config;

import com.TrachAcademy.journalApp.Service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@Profile("dev")
public class SpringSecurity {

    private final UserDetailsServiceImpl userDetailsService;

    // this method encode password from text to hash using passwordEncoder().
    public SpringSecurity(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)   throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/journal/**","/users/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("Admin")
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
