package com.soporte_api_spring_boot.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {

    private Integer idTicket;

    private Integer idCliente;

    private String asunto;

    private String descripcion;

    private String estado;

    private LocalDateTime fechaCreacion;
}