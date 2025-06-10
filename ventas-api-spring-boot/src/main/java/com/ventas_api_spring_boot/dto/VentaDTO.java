package com.ventas_api_spring_boot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {

    private Integer idVenta;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Integer idCliente;

    @NotNull(message = "El ID del vendedor es obligatorio")
    private Integer idVendedor;

    private LocalDateTime fechaHora;

    private Integer total;

    @NotNull(message = "Debe haber al menos un detalle de venta")
    private List<DetalleVentaDTO> detalles;
}
