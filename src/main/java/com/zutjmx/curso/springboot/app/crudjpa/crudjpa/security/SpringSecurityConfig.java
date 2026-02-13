package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.security.filter.JwtAuthenticationFilter;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

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
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .csrf(config -> config.disable())
            .sessionManagement(
                management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .build();
    }

}
