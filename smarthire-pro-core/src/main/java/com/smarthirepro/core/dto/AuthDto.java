package com.smarthirepro.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AuthDto (
    @Email @NotNull String email,
    @NotNull String senha
) {}

