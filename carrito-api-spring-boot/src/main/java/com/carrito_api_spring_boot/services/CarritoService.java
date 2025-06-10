package com.carrito_api_spring_boot.services;

import com.carrito_api_spring_boot.dto.CarritoDTO;
import com.carrito_api_spring_boot.models.Carrito;
import com.carrito_api_spring_boot.repository.CarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository repository;

    public List<CarritoDTO> listar() {
        return repository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public CarritoDTO obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    public CarritoDTO crearDesdeDTO(CarritoDTO dto) {
        Carrito carrito = convertirAEntidad(dto);
        return convertirADTO(repository.save(carrito));
    }

    public CarritoDTO actualizar(Integer id, CarritoDTO dto) {
        Carrito existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        existente.setIdCliente(dto.getIdCliente());
        existente.setIdProducto(dto.getIdProducto());
        existente.setCantidad(dto.getCantidad());
        existente.setActivo(dto.getActivo());

        return convertirADTO(repository.save(existente));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private CarritoDTO convertirADTO(Carrito carrito) {
        return new CarritoDTO(
                carrito.getIdCarrito(),
                carrito.getIdCliente(),
                carrito.getIdProducto(),
                carrito.getCantidad(),
                carrito.getActivo()
        );
    }

    private Carrito convertirAEntidad(CarritoDTO dto) {
        return new Carrito(
                dto.getIdCarrito(),
                dto.getIdCliente(),
                dto.getIdProducto(),
                dto.getCantidad(),
                dto.getActivo()
        );
    }
}
