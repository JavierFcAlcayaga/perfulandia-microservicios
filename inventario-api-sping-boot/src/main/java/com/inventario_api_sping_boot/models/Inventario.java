package com.inventario_api_sping_boot.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Integer idInventario;

    @Column(name = "id_producto", nullable = false)
    private Integer idProducto; // Se enlaza con productos por ID (relación externa lógica, sin @ManyToOne por ahora por la independencia de las tablas)

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Boolean activo = true;
}

