package ru.ydubovitsky.engineerBlog.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.ydubovitsky.engineerBlog.config.VariablesConfig;

@Configuration
@RequiredArgsConstructor
public class CORSConfig {

    private final VariablesConfig variablesConfig;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins(variablesConfig.getAllowedOriginsArray().split(","));
            }
        };
    }
}
