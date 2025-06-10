package com.inventario_api_sping_boot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI inventarioOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Inventario - Perfulandia")
                        .version("1.0")
                        .description("Microservicio para gestionar el stock de productos disponibles en Perfulandia"));
    }
}
