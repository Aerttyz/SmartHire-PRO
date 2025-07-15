package com.smarthirepro.api.services;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.smarthirepro.core.service.IAvaliacaoLlm;

@Component
public class AvaliacaoDtImpl implements IAvaliacaoLlm {

    @Override
    public String avaliacaoPrompt(UUID cargoId, UUID candidatoId) {
        String payload = "Avaliacao prompt for cargoId: " + cargoId + " and candidatoId: " + candidatoId;
        return payload;
    }

}
