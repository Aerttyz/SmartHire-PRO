package com.smarthirepro.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthirepro.domain.model.CargoGenerico;
import com.smarthirepro.domain.repositories.CargoRepository;

public interface CargoJpaRepository extends JpaRepository<CargoGenerico, UUID>, CargoRepository {

    @Override
    Optional<CargoGenerico> findById(UUID id);

}
