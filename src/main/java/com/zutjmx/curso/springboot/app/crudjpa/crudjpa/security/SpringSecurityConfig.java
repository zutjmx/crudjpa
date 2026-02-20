package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.security.filter.JwtAuthenticationFilter;
import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.security.filter.JwtValidationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
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
                .requestMatchers(HttpMethod.GET,"/api/usuarios").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/usuarios/registrar").permitAll()
                // .requestMatchers(HttpMethod.POST,"/api/usuarios").hasRole("ADMIN")
                // .requestMatchers(
                //     HttpMethod.GET,
                //     "/api/productos/listar",
                //     "/api/productos/{id}"
                // ).hasAnyRole("USER", "ADMIN")
                // .requestMatchers(HttpMethod.POST,"/api/productos/crear").hasRole("ADMIN")
                // .requestMatchers(HttpMethod.PUT,"/api/productos/{id}").hasRole("ADMIN")
                // .requestMatchers(HttpMethod.DELETE,"/api/productos/{id}").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtValidationFilter(authenticationManager()))
            .csrf(config -> config.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(
                management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;        
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
            new CorsFilter(corsConfigurationSource())
        );

        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
