package com.smarthirepro.core.security;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.smarthirepro.domain.model.Empresa;

public class AuthUtils {
    public static UUID getEmpresaId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Empresa userDetails = (Empresa) authentication.getPrincipal();
        UUID empresaId = userDetails.getId();
        if (empresaId == null) {
            throw new IllegalStateException("Empresa ID não encontrado no contexto de autenticação.");
        }
        return empresaId;
    }
}
