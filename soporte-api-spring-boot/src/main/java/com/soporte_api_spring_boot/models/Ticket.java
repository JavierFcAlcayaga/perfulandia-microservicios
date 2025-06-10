package com.soporte_api_spring_boot.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTicket;

    private Integer idCliente;

    private String asunto;

    private String descripcion;

    private String estado; //"pendiente", "en progreso", "resuelto"

    private LocalDateTime fechaCreacion;
}
