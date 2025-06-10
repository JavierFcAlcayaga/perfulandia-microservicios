package com.gateway.redireccion.gestion;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/proxy/reportes")
public class ReportesProxyController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8089/api/reportes";

    @GetMapping("/ventas-productos")
    public ResponseEntity<?> getReporteVentasPorProducto() {
        String url = BASE_URL + "/ventas-productos";
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"No se pudo obtener el reporte\"}");
        }
    }
}