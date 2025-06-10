package com.envios_api_spring_boot.controllers;

import com.envios_api_spring_boot.dto.EnvioDTO;
import com.envios_api_spring_boot.services.EnvioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService service;

    @GetMapping
    public ResponseEntity<List<EnvioDTO>> listarEnvios() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioDTO> obtenerEnvio(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<EnvioDTO> crearEnvio(@Valid @RequestBody EnvioDTO dto) {
        return ResponseEntity.ok(service.crearDesdeDTO(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvioDTO> actualizarEnvio(@PathVariable Integer id, @Valid @RequestBody EnvioDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}