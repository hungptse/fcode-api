package dev.fcodeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@SpringBootApplication
@Configuration
public class FcodeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FcodeApiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*","localhost:3000")
                        .allowedMethods("*")
                        .allowCredentials(false)
                        .allowedHeaders("Authorization","Content-Type","Access-Control-Allow-Origin")
                        .allowedHeaders("*")
                        .exposedHeaders("Access-Control-Allow-Headers", "Authorization","Content-Type","Access-Control-Allow-Origin");

            }
        };
    }
}
