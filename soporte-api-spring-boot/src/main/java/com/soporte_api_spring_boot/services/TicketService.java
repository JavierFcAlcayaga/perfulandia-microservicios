package com.soporte_api_spring_boot.services;

import com.soporte_api_spring_boot.dto.TicketDTO;
import com.soporte_api_spring_boot.models.Ticket;
import com.soporte_api_spring_boot.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;

    public TicketDTO crearTicket(TicketDTO dto) {
        Ticket ticket = Ticket.builder()
                .asunto(dto.getAsunto())
                .descripcion(dto.getDescripcion())
                .estado("Pendiente")
                .fechaCreacion(LocalDateTime.now())
                .idCliente(dto.getIdCliente())
                .build();

        Ticket guardado = repository.save(ticket);

        return TicketDTO.builder()
                .idTicket(guardado.getIdTicket())
                .asunto(guardado.getAsunto())
                .descripcion(guardado.getDescripcion())
                .estado(guardado.getEstado())
                .fechaCreacion(guardado.getFechaCreacion())
                .idCliente(guardado.getIdCliente())
                .build();
    }

    public List<TicketDTO> listarTickets() {
        return repository.findAll().stream().map(ticket ->
                TicketDTO.builder()
                        .idTicket(ticket.getIdTicket())
                        .asunto(ticket.getAsunto())
                        .descripcion(ticket.getDescripcion())
                        .estado(ticket.getEstado())
                        .fechaCreacion(ticket.getFechaCreacion())
                        .idCliente(ticket.getIdCliente())
                        .build()
        ).toList();
    }
}