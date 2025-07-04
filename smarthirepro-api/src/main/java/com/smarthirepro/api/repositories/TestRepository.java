package com.smarthirepro.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smarthirepro.domain.model.Empresa;
import com.smarthirepro.domain.repositories.EmpresaRepository;

@Repository
public interface TestRepository extends JpaRepository<Empresa, UUID>, EmpresaRepository {
    @Override
    Empresa save(Empresa empresa);
}
