package com.envios_api_spring_boot.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "envio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnvio;

    private Integer idVenta;

    private String direccionDestino;

    private String estado; //"PENDIENTE", "EN CAMINO", "ENTREGADO"

    private LocalDateTime fechaEnvio;

    private LocalDateTime fechaEntrega;
}
