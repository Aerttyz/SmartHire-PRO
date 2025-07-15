package com.smarthirepro.api.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.smarthirepro.api.dtos.AvaliacaoTestDto;
import com.smarthirepro.core.service.impl.AvaliacaoLlmImpl;

@Service
public class AvaliacaoTest {
    private final AvaliacaoLlmImpl<AvaliacaoTestDto> avaliacaoLlmImpl;

    public AvaliacaoTest(AvaliacaoLlmImpl<AvaliacaoTestDto> avaliacaoLlmImpl) {
        this.avaliacaoLlmImpl = avaliacaoLlmImpl;
    }

    public AvaliacaoTestDto realizarAvaliacao(UUID cargoId, UUID candidatoId) {
        return avaliacaoLlmImpl.realizarAvaliacao(cargoId, candidatoId);
    }
}
