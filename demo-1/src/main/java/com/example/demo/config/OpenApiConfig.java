package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("jwt", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                        ))
                .info(new Info()
                        .title("Digital Booking API")
                        .description("Equipo 4: Lucas De Andres, David S. Florez, David Cárdenas, Sofia Diaz Valdez" +
                                " y Francisco Martos")
                        .version("1.1.0")
                        .contact(new Contact()
                                .name("Digital Booking")
                                .url("https://gitlab.ctd.academy/ctd/hispanos/proyecto-integrador-1/proyecto-integrador-0223/0522ft-c2/grupo-04"))
                );
    }
}
