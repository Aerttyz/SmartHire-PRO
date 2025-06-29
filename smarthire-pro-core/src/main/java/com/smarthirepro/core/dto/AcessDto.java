package com.smarthirepro.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcessDto {
    private String token;

    public AcessDto(String token) {
        super();
        this.token = token;
    }
}
