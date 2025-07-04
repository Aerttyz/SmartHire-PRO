package com.smarthirepro.core.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthirepro.domain.model.CargoGenerico;
import com.smarthirepro.domain.repositories.CargoRepository;

@Service
public class CargoServiceImpl implements ICargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public CargoGenerico listarPorId(UUID id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo not found with id: " + id));
    }

}
