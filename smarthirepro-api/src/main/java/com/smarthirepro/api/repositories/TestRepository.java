package com.smarthirepro.api.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.smarthirepro.domain.model.Empresa;
import com.smarthirepro.domain.repositories.EmpresaRepository;

@Repository
public class TestRepository implements EmpresaRepository {

    private final Map<UUID, Empresa> banco = new HashMap<>();

    @Override
    public Optional<Empresa> findByEmail(String email) {

        return banco.values().stream()
                .filter(empresa -> empresa.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public Empresa save(Empresa empresa) {
        UUID id = empresa.getId() != null ? empresa.getId() : UUID.randomUUID();
        empresa.setId(id);
        banco.put(id, empresa);
        return empresa;
    }

    @Override
    public Optional<Empresa> findById(UUID id) {
        // Implement the method to find an Empresa by ID
        return Optional.ofNullable(banco.get(id));
    }

    @Override
    public Optional<Empresa> findByCnpj(String cnpj) {
        return banco.values().stream()
                .filter(empresa -> empresa.getCnpj().equalsIgnoreCase(cnpj))
                .findFirst();
    }

}
