package com.smarthirepro.domain.repositories;

import java.util.Optional;

import com.smarthirepro.domain.model.Candidato;

public interface CandidatoRepository {
    Candidato save(Candidato candidato);

    Optional<Candidato> findByEmail(String email);
}
