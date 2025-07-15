package com.smarthirepro.core.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.smarthirepro.core.dto.AvaliacaoDto;
import com.smarthirepro.core.service.IAvaliacaoLlm;

public class AvaliacaoLlmImpl<T extends AvaliacaoDto> {
    private final IAvaliacaoLlm avaliacaoLlm;
    private final String flaskUrl = "http://localhost:5000/avaliar";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Class<T> responseType;

    public AvaliacaoLlmImpl(IAvaliacaoLlm avaliacaoLlm, Class<T> responseType) {
        this.avaliacaoLlm = avaliacaoLlm;
        this.responseType = responseType;
    }

    public T realizarAvaliacao(UUID cargoId, UUID candidatoId) {
        Map<String, String> payload = new HashMap<>();
        payload.put("prompt", avaliacaoLlm.avaliacaoPrompt(cargoId, candidatoId));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<T> response = restTemplate.postForEntity(
                    flaskUrl, requestEntity, responseType);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error during evaluation: " + e.getMessage(), e);
        }
    }
}
