package com.inventario_api_sping_boot.services;

import com.inventario_api_sping_boot.dto.InventarioDTO;
import com.inventario_api_sping_boot.models.Inventario;
import com.inventario_api_sping_boot.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository repo;

    public List<InventarioDTO> listar() {
        return repo.findAll().stream()
                .map(i -> new InventarioDTO(
                        i.getIdInventario(),
                        i.getIdProducto(),
                        i.getCantidad(),
                        i.getActivo()
                ))
                .toList();
    }

    public InventarioDTO obtenerPorId(Integer id) {
        Inventario i = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + id));
        return new InventarioDTO(
                i.getIdInventario(),
                i.getIdProducto(),
                i.getCantidad(),
                i.getActivo()
        );
    }

    public InventarioDTO crearDesdeDTO(InventarioDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setIdProducto(dto.getIdProducto());
        inventario.setCantidad(dto.getCantidad());
        inventario.setActivo(dto.getActivo() != null ? dto.getActivo() : true); // true por defecto

        Inventario guardado = repo.save(inventario);

        return new InventarioDTO(
                guardado.getIdInventario(),
                guardado.getIdProducto(),
                guardado.getCantidad(),
                guardado.getActivo()
        );
    }

    public InventarioDTO actualizar(Integer id, InventarioDTO dto) {
        Inventario inventario = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + id));

        inventario.setIdProducto(dto.getIdProducto());
        inventario.setCantidad(dto.getCantidad());
        inventario.setActivo(dto.getActivo());

        Inventario actualizado = repo.save(inventario);

        return new InventarioDTO(
                actualizado.getIdInventario(),
                actualizado.getIdProducto(),
                actualizado.getCantidad(),
                actualizado.getActivo()
        );
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    // NUEVO: Obtener inventario por idProducto
    public InventarioDTO obtenerPorIdProducto(Integer idProducto) {
        Inventario inventario = repo.findByIdProducto(idProducto)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No hay inventario para el producto " + idProducto
                ));

        return new InventarioDTO(
                inventario.getIdInventario(),
                inventario.getIdProducto(),
                inventario.getCantidad(),
                inventario.getActivo()
        );
    }

    // NUEVO: Descontar cantidad de stock
    public void descontarStock(Integer idProducto, int cantidadDescontar) {
        Inventario inventario = repo.findByIdProducto(idProducto)
                .orElseThrow(() -> new RuntimeException("No se encontró inventario del producto " + idProducto));

        if (inventario.getCantidad() < cantidadDescontar) {
            throw new RuntimeException("Stock insuficiente para el producto ID: " + idProducto);
        }

        inventario.setCantidad(inventario.getCantidad() - cantidadDescontar);
        repo.save(inventario);
    }
}
