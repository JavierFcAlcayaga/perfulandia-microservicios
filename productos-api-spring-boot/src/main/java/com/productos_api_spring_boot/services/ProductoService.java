package com.productos_api_spring_boot.services;

import com.productos_api_spring_boot.dto.ProductoDTO;
import com.productos_api_spring_boot.models.Producto;
import com.productos_api_spring_boot.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repo;

    public List<ProductoDTO> listar() {
        return repo.findAll().stream()
                .map(p -> new ProductoDTO(
                        p.getIdProducto(),
                        p.getNombre(),
                        p.getDescripcion(),
                        p.getPrecioUnitario(),
                        p.getCategoria(),
                        p.getActivo()
                ))
                .toList();
    }

    public ProductoDTO obtenerPorId(Integer id) {
        Producto p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return new ProductoDTO(
                p.getIdProducto(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecioUnitario(),
                p.getCategoria(),
                p.getActivo()
        );
    }

    public ProductoDTO crearDesdeDTO(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioUnitario(dto.getPrecioUnitario());
        producto.setCategoria(dto.getCategoria());
        producto.setActivo(dto.getActivo() != null ? dto.getActivo() : true); // true por defecto

        Producto guardado = repo.save(producto);

        return new ProductoDTO(
                guardado.getIdProducto(),
                guardado.getNombre(),
                guardado.getDescripcion(),
                guardado.getPrecioUnitario(),
                guardado.getCategoria(),
                guardado.getActivo()
        );
    }

    public ProductoDTO actualizar(Integer id, ProductoDTO dto) {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioUnitario(dto.getPrecioUnitario());
        producto.setCategoria(dto.getCategoria());
        producto.setActivo(dto.getActivo());

        Producto actualizado = repo.save(producto);

        return new ProductoDTO(
                actualizado.getIdProducto(),
                actualizado.getNombre(),
                actualizado.getDescripcion(),
                actualizado.getPrecioUnitario(),
                actualizado.getCategoria(),
                actualizado.getActivo()
        );
    }

    public Producto guardar(Producto p) {
        return repo.save(p);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
