package com.carrito_api_spring_boot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI carritoOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Carrito - Perfulandia")
                .version("1.0")
                .description("Microservicio para gestionar el carrito de compras de los clientes en Perfulandia"));
    }
}
