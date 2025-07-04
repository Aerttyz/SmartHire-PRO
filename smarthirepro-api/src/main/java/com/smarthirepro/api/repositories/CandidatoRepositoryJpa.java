package com.smarthirepro.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smarthirepro.domain.model.Candidato;
import com.smarthirepro.domain.repositories.CandidatoRepository;

@Repository
public interface CandidatoRepositoryJpa extends JpaRepository<Candidato, UUID>, CandidatoRepository {

    @Override
    Candidato save(Candidato candidato);

    @Override
    Optional<Candidato> findByEmail(String email);
}
