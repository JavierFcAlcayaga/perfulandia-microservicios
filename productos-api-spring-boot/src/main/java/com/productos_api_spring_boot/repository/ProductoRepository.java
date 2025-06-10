package com.productos_api_spring_boot.repository;

import com.productos_api_spring_boot.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    //algunos metodos van aca si los necesitamos mas adelante
}

