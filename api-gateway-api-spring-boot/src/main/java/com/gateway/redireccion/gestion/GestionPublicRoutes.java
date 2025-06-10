package com.gateway.redireccion.gestion;

public class GestionPublicRoutes {

    // Rutas públicas GET (visibles sin token)
    public static final String[] GESTION_PUBLIC_GET = {
        "/api/proxy/usuarios",
        "/api/proxy/usuarios/",
        "/api/proxy/inventarios",
        "/api/proxy/inventarios/",
        "/api/proxy/carrito",
        "/api/proxy/carrito/",
        "/api/proxy/ventas",
        "/api/proxy/ventas/",    
        "/api/proxy/reportes/ventas-productos",
        "/api/proxy/reportes/ventas-productos/",
        "/api/proxy/ventas-productos",
        "/api/proxy/ventas-productos/",
        "api/proxy/soporte",
        "api/proxy/soporte/"
    };

    // Si más adelante decidimos permitir el registro de productos de forma pública, lo definiremos acá.
    // Por ahora lo dejamos vacío para que POST esté protegido.
    public static final String[] GESTION_PUBLIC_POST = {
        "/api/proxy/usuarios",
        "/api/proxy/soporte",
        "/api/proxy/soporte/"
    };
    
}
