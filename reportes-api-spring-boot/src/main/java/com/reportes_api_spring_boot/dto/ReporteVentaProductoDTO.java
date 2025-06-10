package com.reportes_api_spring_boot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReporteVentaProductoDTO {
    private Integer idProducto;
    private String nombreProducto;
    private Integer totalUnidadesVendidas;
    private Integer totalIngresos;
}