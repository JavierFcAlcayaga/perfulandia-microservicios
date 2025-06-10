package com.gateway.jwt.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.http.HttpMethod;

import static com.gateway.jwt.security.PublicRoutes.*; // JWT: login, ping
import static com.gateway.redireccion.gestion.GestionPublicRoutes.*; // GESTIÓN: usuarios y productos

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                //Rutas públicas JWT (login, ping)
                .requestMatchers(HttpMethod.POST, PUBLIC_POST).permitAll()
                .requestMatchers(HttpMethod.GET, PUBLIC_GET).permitAll()

                //Rutas públicas GESTIÓN (usuarios y productos GET/POST públicos)
                .requestMatchers(HttpMethod.GET, GESTION_PUBLIC_GET).permitAll()
                .requestMatchers(HttpMethod.POST, GESTION_PUBLIC_POST).permitAll()

                //Todo lo demás requiere token
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
