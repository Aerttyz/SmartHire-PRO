package com.smarthirepro.core.service;

import java.util.UUID;

public interface IAvaliacaoLlm {
    String avaliacaoPrompt(UUID cargoId, UUID candidatoId);
}
