package com.soporte_api_spring_boot.controllers;

import com.soporte_api_spring_boot.dto.TicketDTO;
import com.soporte_api_spring_boot.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/soporte")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;

    @PostMapping
    public ResponseEntity<TicketDTO> crearTicket(@Valid @RequestBody TicketDTO dto) {
        TicketDTO creado = service.crearTicket(dto);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> listarTickets() {
        return ResponseEntity.ok(service.listarTickets());
    }
}