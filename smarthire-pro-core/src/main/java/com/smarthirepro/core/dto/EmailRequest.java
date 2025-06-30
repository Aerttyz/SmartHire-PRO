package com.smarthirepro.core.dto;

import jakarta.validation.constraints.Email;

public record EmailRequest(
        @Email
        String destinatario,
        String assunto,
        String corpo
) {}