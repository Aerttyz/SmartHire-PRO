package com.smarthirepro.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthirepro.domain.model.Curriculo;
import com.smarthirepro.domain.repositories.CurriculoRepository;

public interface CurriculoRepositoryJpa extends JpaRepository<Curriculo, UUID>, CurriculoRepository {

    @Override
    Curriculo save(Curriculo curriculo);

}
