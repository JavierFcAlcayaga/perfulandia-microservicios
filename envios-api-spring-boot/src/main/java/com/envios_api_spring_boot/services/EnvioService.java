package com.envios_api_spring_boot.services;

import com.envios_api_spring_boot.dto.EnvioDTO;
import com.envios_api_spring_boot.models.Envio;
import com.envios_api_spring_boot.repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvioService {

    private final EnvioRepository repo;

    public List<EnvioDTO> listar() {
        return repo.findAll().stream().map(this::convertirADTO).toList();
    }

    public EnvioDTO obtenerPorId(Integer id) {
        Envio envio = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con ID: " + id));
        return convertirADTO(envio);
    }

    public EnvioDTO crearDesdeDTO(EnvioDTO dto) {
        Envio envio = Envio.builder()
                .idVenta(dto.getIdVenta())
                .direccionDestino(dto.getDireccionDestino())
                .estado(dto.getEstado())
                .fechaEnvio(dto.getFechaEnvio())
                .fechaEntrega(dto.getFechaEntrega())
                .build();

        Envio guardado = repo.save(envio);
        return convertirADTO(guardado);
    }

    public EnvioDTO actualizar(Integer id, EnvioDTO dto) {
        Envio envio = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con ID: " + id));

        envio.setIdVenta(dto.getIdVenta());
        envio.setDireccionDestino(dto.getDireccionDestino());
        envio.setEstado(dto.getEstado());
        envio.setFechaEnvio(dto.getFechaEnvio());
        envio.setFechaEntrega(dto.getFechaEntrega());

        Envio actualizado = repo.save(envio);
        return convertirADTO(actualizado);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    private EnvioDTO convertirADTO(Envio envio) {
        return EnvioDTO.builder()
                .idEnvio(envio.getIdEnvio())
                .idVenta(envio.getIdVenta())
                .direccionDestino(envio.getDireccionDestino())
                .estado(envio.getEstado())
                .fechaEnvio(envio.getFechaEnvio())
                .fechaEntrega(envio.getFechaEntrega())
                .build();
    }
}
