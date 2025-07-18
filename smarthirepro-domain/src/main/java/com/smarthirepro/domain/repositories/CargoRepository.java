package com.smarthirepro.domain.repositories;

import java.util.Optional;
import java.util.UUID;

import com.smarthirepro.domain.model.CargoGenerico;

public interface CargoRepository<T extends CargoGenerico> {

    Optional<T> findById(UUID id);
}
