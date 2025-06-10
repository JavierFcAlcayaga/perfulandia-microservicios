package com.envios_api_spring_boot.dto;

import lombok.*;    
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EnvioDTO {
    private Integer idEnvio;
    private Integer idVenta;
    private String direccionDestino;
    private String estado;
    private LocalDateTime fechaEnvio;
    private LocalDateTime fechaEntrega;
}