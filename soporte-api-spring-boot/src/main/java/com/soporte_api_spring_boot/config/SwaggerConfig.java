package com.soporte_api_spring_boot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI soporteOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Soporte - Perfulandia")
                        .version("1.0")
                        .description("Microservicio para gestionar tickets de soporte en la plataforma Perfulandia"));
    }
}