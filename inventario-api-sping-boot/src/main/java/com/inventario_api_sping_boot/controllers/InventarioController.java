package com.inventario_api_sping_boot.controllers;

import com.inventario_api_sping_boot.dto.InventarioDTO;
import com.inventario_api_sping_boot.services.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listarInventario() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> obtenerInventario(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<InventarioDTO> crearInventario(@Valid @RequestBody InventarioDTO dto) {
        return ResponseEntity.ok(service.crearDesdeDTO(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizarInventario(@PathVariable Integer id, @Valid @RequestBody InventarioDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // NUEVO: Obtener inventario por id de producto
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<InventarioDTO> obtenerPorIdProducto(@PathVariable Integer idProducto) {
        return ResponseEntity.ok(service.obtenerPorIdProducto(idProducto));
    }

    // NUEVO: Descontar stock desde venta
    @PutMapping("/descontar/{idProducto}")
    public ResponseEntity<Void> descontarStock(
            @PathVariable Integer idProducto,
            @RequestParam("cantidad") Integer cantidad) {
        service.descontarStock(idProducto, cantidad);
        return ResponseEntity.noContent().build();
    }
}
