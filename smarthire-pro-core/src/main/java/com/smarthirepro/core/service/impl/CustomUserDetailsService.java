package com.smarthirepro.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.smarthirepro.domain.model.Empresa;
import com.smarthirepro.domain.repositories.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Empresa empresa = empresaRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Empresa not found with email: " + username));
        return empresa;
    }

}
