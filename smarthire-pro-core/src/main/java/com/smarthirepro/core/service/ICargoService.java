package com.smarthirepro.core.service;

import java.util.UUID;

import com.smarthirepro.domain.model.CargoGenerico;

public interface ICargoService<T extends CargoGenerico> {
    T listarPorId(UUID id);
}
