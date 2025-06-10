package com.gateway.redireccion.gestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/proxy/soporte")
public class SoporteProxyController {

    private final RestTemplate restTemplate;

    @Autowired
    public SoporteProxyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String BASE_URL = "http://localhost:8090/api/soporte";

    @GetMapping
    public ResponseEntity<String> listarTickets() {
        String url = BASE_URL;
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    @PostMapping
    public ResponseEntity<String> crearTicket(@RequestBody String body) {
        String url = BASE_URL;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        return restTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }
}
