package com.smarthirepro.core.service.impl;

import java.util.List;
import java.util.UUID;

import com.smarthirepro.domain.model.CargoGenerico;

public abstract class AnaliseTemplate<T extends CargoGenerico> {

    public final String runAnalise(UUID cargoId) {
        List<String> criterios = definirCriterios(cargoId);
        return executarAnalise(cargoId, criterios);

    }

    protected abstract List<String> definirCriterios(UUID cargoId);

    protected abstract String executarAnalise(UUID cargoId, List<String> criterios);

}
