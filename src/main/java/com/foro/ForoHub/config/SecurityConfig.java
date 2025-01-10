package com.foro.ForoHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/topics/**").permitAll()  // Permite acceso sin autenticación a /topics
                        .requestMatchers("/users/**").permitAll()   // Permite acceso sin autenticación a /users
                        .requestMatchers("/courses/**").permitAll()   // Permite acceso sin autenticación a /courses
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
