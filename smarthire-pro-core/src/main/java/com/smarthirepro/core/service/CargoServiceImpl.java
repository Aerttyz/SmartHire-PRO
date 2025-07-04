package com.smarthirepro.core.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.smarthirepro.domain.model.CargoGenerico;
import com.smarthirepro.domain.repositories.CargoRepository;

@Service
public class CargoServiceImpl<T extends CargoGenerico> implements ICargoService<T> {

    private final CargoRepository<T> cargoRepository;

    public CargoServiceImpl(CargoRepository<T> cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @Override
    public T listarPorId(UUID id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo not found with id: " + id));
    }

}
