package com.ventas_api_spring_boot.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Integer precioUnitario;
    private String categoria;
    private Boolean activo;
}
