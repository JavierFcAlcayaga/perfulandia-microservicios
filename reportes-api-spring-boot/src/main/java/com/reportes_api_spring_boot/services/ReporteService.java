package com.reportes_api_spring_boot.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.reportes_api_spring_boot.dto.ReporteVentaProductoDTO;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final RestTemplate restTemplate;

    public List<ReporteVentaProductoDTO> generarReporteVentasPorProducto() {
        try {
            // 1. Obtener todas las ventas desde el microservicio de ventas
            String urlVentas = "http://localhost:8086/api/ventas";
            VentaDTO[] ventas = restTemplate.getForObject(urlVentas, VentaDTO[].class);

            if (ventas == null || ventas.length == 0) {
                System.out.println("⚠️ No se encontraron ventas registradas.");
                return Collections.emptyList();
            }

            // 2. Resumen de ventas por producto: [cantidadTotal, ingresoTotal]
            Map<Integer, int[]> resumen = new HashMap<>();

            for (VentaDTO venta : ventas) {
                if (venta.getDetalles() == null) continue;

                for (DetalleVentaDTO detalle : venta.getDetalles()) {
                    if (detalle == null) continue;

                    int[] datos = resumen.getOrDefault(detalle.getIdProducto(), new int[2]);
                    datos[0] += detalle.getCantidad();
                    datos[1] += detalle.getCantidad() * detalle.getPrecioUnitario().intValue();
                    resumen.put(detalle.getIdProducto(), datos);
                }
            }

            // 3. Obtener nombre de cada producto desde el microservicio productos
            List<ReporteVentaProductoDTO> reporte = new ArrayList<>();

            for (Map.Entry<Integer, int[]> entry : resumen.entrySet()) {
                Integer idProd = entry.getKey();
                int cantidad = entry.getValue()[0];
                int total = entry.getValue()[1];

                String urlProducto = "http://localhost:8083/api/productos/" + idProd;
                ProductoDTO producto = null;

                try {
                    producto = restTemplate.getForObject(urlProducto, ProductoDTO.class);
                } catch (Exception e) {
                    System.out.println("No se pudo obtener producto con ID " + idProd);
                }

                reporte.add(ReporteVentaProductoDTO.builder()
                        .idProducto(idProd)
                        .nombreProducto(producto != null ? producto.getNombre() : "Desconocido")
                        .totalUnidadesVendidas(cantidad)
                        .totalIngresos(total)
                        .build());
            }

            return reporte;

        } catch (Exception e) {
            System.out.println("Error generando reporte: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // DTO interno para consumir microservicio de ventas
    @Data
    static class VentaDTO {
        private List<DetalleVentaDTO> detalles;
    }

    @Data
    static class DetalleVentaDTO {
        private Integer idProducto;
        private Integer cantidad;
        private Double precioUnitario;
    }

    // DTO interno para obtener nombre desde microservicio productos
    @Data
    static class ProductoDTO {
        private Integer idProducto;
        private String nombre;
    }
}