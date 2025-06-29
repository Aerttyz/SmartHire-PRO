package com.smarthirepro.domain.repositories;

import java.util.Optional;
import java.util.UUID;

import com.smarthirepro.domain.model.Empresa;

public interface EmpresaRepository {
    Optional<Empresa> findByEmail(String email);

    Empresa save(Empresa empresa);

    Optional<Empresa> findById(UUID id);

    Optional<Empresa> findByCnpj(String cnpj);
}
