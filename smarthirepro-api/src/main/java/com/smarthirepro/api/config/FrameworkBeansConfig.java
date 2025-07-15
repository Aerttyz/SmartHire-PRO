package com.smarthirepro.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smarthirepro.api.dtos.AvaliacaoTestDto;
import com.smarthirepro.core.service.IAvaliacaoLlm;
import com.smarthirepro.core.service.impl.AvaliacaoLlmImpl;

@Configuration
public class FrameworkBeansConfig {

    @Bean
    public AvaliacaoLlmImpl<AvaliacaoTestDto> avaliacaoLlmImpl(IAvaliacaoLlm avaliacaoLlm) {
        return new AvaliacaoLlmImpl<>(avaliacaoLlm, AvaliacaoTestDto.class);
    }
}
