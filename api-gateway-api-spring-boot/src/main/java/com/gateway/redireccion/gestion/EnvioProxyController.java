package com.gateway.redireccion.gestion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.Map;

@RestController
@RequestMapping("/api/envios")
public class EnvioProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${envios.service.url:http://localhost:8087}")
    private String baseUrl;

    @GetMapping
    public ResponseEntity<?> listar() {
        return restTemplate.exchange(baseUrl + "/api/envios", HttpMethod.GET, null, String.class);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> envio) {
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(envio);
        return restTemplate.exchange(baseUrl + "/api/envios", HttpMethod.POST, request, String.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Map<String, Object> envio) {
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(envio);
        return restTemplate.exchange(baseUrl + "/api/envios/" + id, HttpMethod.PUT, request, String.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        return restTemplate.exchange(baseUrl + "/api/envios/" + id, HttpMethod.DELETE, null, String.class);
    }
}

