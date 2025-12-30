package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();        
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {        
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET,"/api/productos/listar").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/usuarios").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/usuarios/registrar").permitAll()
                .anyRequest().authenticated()
            )
            .csrf(config -> config.disable())
            .sessionManagement(
                management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .build();
    }

}
