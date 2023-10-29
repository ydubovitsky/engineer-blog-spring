package ru.ydubovitsky.engineerBlog.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.ydubovitsky.engineerBlog.config.VariablesConfig;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CORSConfig implements WebMvcConfigurer {

    private final VariablesConfig variablesConfig;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        log.info(String.format("CORS AllowedOrigins %s", variablesConfig.getAllowedOriginsArray()));
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(variablesConfig.getAllowedOriginsArray())
                        .allowedMethods("*");
            }
        };
    }
}
