package dev.dominicbrauer.web_wordle_tim24.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.lang.NonNull;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")  // Alle Endpunkte
                .allowedOrigins("http://localhost:4321") // Erlaube nur Anfragen von diesem Ursprung
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Erlaube nur diese HTTP-Methoden
                .allowedHeaders("*") // Erlaube alle Header
                .allowCredentials(true); // Erlaube das Senden von Cookies/Anmeldeinformationen
    }
}
