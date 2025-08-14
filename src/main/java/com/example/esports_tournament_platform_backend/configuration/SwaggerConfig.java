package com.example.esports_tournament_platform_backend.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI esportsTournamentOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Esports Tournament Platform API")
                        .description("API documentation for the Esports Tournament Platform backend")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Huynh Quoc Tien")
                                .email("quoctien01062003@gmail.com")
                                .url("https://yourwebsite.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Repository")
                        .url("https://github.com/HuynhQuocTien/esports-tournament-platform-backend"));
    }
}

