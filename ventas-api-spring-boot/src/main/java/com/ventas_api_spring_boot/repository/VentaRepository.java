package com.ventas_api_spring_boot.repository;

import com.ventas_api_spring_boot.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
