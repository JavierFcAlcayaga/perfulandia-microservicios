package com.ventas_api_spring_boot.repository;

import com.ventas_api_spring_boot.models.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
}
