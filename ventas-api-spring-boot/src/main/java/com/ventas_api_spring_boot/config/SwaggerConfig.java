package com.ventas_api_spring_boot.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ventaOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Ventas - Perfulandia")
                .version("1.0")
                .description("Microservicio para gestionar las ventas en Perfulandia")
            );
    }

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
