package com.ventas_api_spring_boot.controllers;

import com.ventas_api_spring_boot.dto.VentaDTO;
import com.ventas_api_spring_boot.services.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@Valid @RequestBody VentaDTO dto) {
        VentaDTO ventaCreada = ventaService.crearVentaDesdeDTO(dto);
        return ResponseEntity.ok(ventaCreada);
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listarVentas() {
        List<VentaDTO> ventas = ventaService.listarVentas();
        return ResponseEntity.ok(ventas);
    }
}
