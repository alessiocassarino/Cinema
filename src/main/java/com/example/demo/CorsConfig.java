package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
    public class CorsConfig {

        @Bean
        public CorsFilter corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedOrigin("http://localhost:4200/"); // Consentire tutte le origini dalla specifica porta
            config.addAllowedHeader("*"); // Consentire tutte le intestazioni
            config.addAllowedMethod("*"); // Consentire tutti i metodi HTTP
            source.registerCorsConfiguration("/**", config); // Applicare la configurazione a tutti i percorsi
            return new CorsFilter(source);
        }


    }

