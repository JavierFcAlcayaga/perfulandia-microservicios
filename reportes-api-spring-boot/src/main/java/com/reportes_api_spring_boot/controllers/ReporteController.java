package com.reportes_api_spring_boot.controllers;

import com.reportes_api_spring_boot.dto.ReporteVentaProductoDTO;
import com.reportes_api_spring_boot.services.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService service;

    @GetMapping("/ventas-productos")
    public ResponseEntity<List<ReporteVentaProductoDTO>> generarReporteVentasPorProducto() {
        return ResponseEntity.ok(service.generarReporteVentasPorProducto());
    }
}
