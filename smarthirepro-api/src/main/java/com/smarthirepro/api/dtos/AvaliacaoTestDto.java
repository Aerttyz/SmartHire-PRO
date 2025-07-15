package com.smarthirepro.api.dtos;

import com.smarthirepro.core.dto.AvaliacaoDto;

public class AvaliacaoTestDto extends AvaliacaoDto {
    private String teste;

    public AvaliacaoTestDto() {
    }

    public AvaliacaoTestDto(String teste) {
        this.teste = teste;
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }
}
