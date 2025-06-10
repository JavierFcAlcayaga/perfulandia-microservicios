package com.ventas_api_spring_boot.services;

import com.ventas_api_spring_boot.dto.DetalleVentaDTO;
import com.ventas_api_spring_boot.dto.ProductoDTO;
import com.ventas_api_spring_boot.dto.VentaDTO;
import com.ventas_api_spring_boot.models.DetalleVenta;
import com.ventas_api_spring_boot.models.Venta;
import com.ventas_api_spring_boot.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final RestTemplate restTemplate;

    public VentaDTO crearVentaDesdeDTO(VentaDTO dto) {

        List<DetalleVenta> detalles = dto.getDetalles().stream().map(det -> {
            // 1. Obtener producto desde servicio-productos
            String urlProducto = "http://localhost:8083/api/productos/" + det.getIdProducto();
            ProductoDTO producto = restTemplate.getForObject(urlProducto, ProductoDTO.class);
            int precioUnitario = (producto != null) ? producto.getPrecioUnitario() : 0;

            // 2. Verificar stock desde servicio-inventario
            String urlInventario = "http://localhost:8084/api/inventarios/producto/" + det.getIdProducto();
            InventarioDTO inventario;

            try {
                ResponseEntity<InventarioDTO> response = restTemplate.getForEntity(urlInventario, InventarioDTO.class);
                inventario = response.getBody();
            } catch (HttpClientErrorException e) {
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new RuntimeException("No hay inventario registrado para el producto ID: " + det.getIdProducto());
                } else {
                    throw new RuntimeException("Error al consultar inventario del producto ID: " + det.getIdProducto());
                }
            }

            if (inventario == null || inventario.getCantidad() < det.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto ID: " + det.getIdProducto());
            }

            // 3. Descontar stock
            String urlDescontar = "http://localhost:8084/api/inventarios/descontar/" + det.getIdProducto()
                    + "?cantidad=" + det.getCantidad();
            restTemplate.put(urlDescontar, null);

            // 4. Retornar detalle armado
            return DetalleVenta.builder()
                    .idProducto(det.getIdProducto())
                    .cantidad(det.getCantidad())
                    .precioUnitario(precioUnitario)
                    .build();
        }).toList();

        int total = detalles.stream()
                .mapToInt(d -> d.getPrecioUnitario() * d.getCantidad())
                .sum();

        Venta venta = Venta.builder()
                .idCliente(dto.getIdCliente())
                .idVendedor(dto.getIdVendedor())
                .fechaHora(LocalDateTime.now())
                .total(total)
                .detalles(detalles)
                .build();

        detalles.forEach(d -> d.setVenta(venta));

        Venta ventaGuardada = ventaRepository.save(venta);

        return VentaDTO.builder()
                .idVenta(ventaGuardada.getIdVenta())
                .idCliente(ventaGuardada.getIdCliente())
                .idVendedor(ventaGuardada.getIdVendedor())
                .fechaHora(ventaGuardada.getFechaHora())
                .total(ventaGuardada.getTotal())
                .detalles(ventaGuardada.getDetalles().stream().map(det ->
                        DetalleVentaDTO.builder()
                                .idDetalle(det.getIdDetalle())
                                .idProducto(det.getIdProducto())
                                .cantidad(det.getCantidad())
                                .precioUnitario(det.getPrecioUnitario())
                                .build()
                ).toList())
                .build();
    }

    public List<VentaDTO> listarVentas() {
        return ventaRepository.findAll().stream().map(venta ->
                VentaDTO.builder()
                        .idVenta(venta.getIdVenta())
                        .idCliente(venta.getIdCliente())
                        .idVendedor(venta.getIdVendedor())
                        .fechaHora(venta.getFechaHora())
                        .total(venta.getTotal())
                        .detalles(venta.getDetalles().stream().map(det ->
                                DetalleVentaDTO.builder()
                                        .idDetalle(det.getIdDetalle())
                                        .idProducto(det.getIdProducto())
                                        .cantidad(det.getCantidad())
                                        .precioUnitario(det.getPrecioUnitario())
                                        .build()
                        ).toList())
                        .build()
        ).toList();
    }

    // DTO interno para recibir inventario
    @lombok.Data
    static class InventarioDTO {
        private Integer idInventario;
        private Integer idProducto;
        private Integer cantidad;
        private Boolean activo;
    }
}
