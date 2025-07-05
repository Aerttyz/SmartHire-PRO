package com.smarthirepro.core.service.impl;

import java.util.List;
import java.util.UUID;

import com.smarthirepro.domain.model.CargoGenerico;

public abstract class AnaliseTemplate<T extends CargoGenerico> {

    public final String runAnalise(UUID cargoId) {
        List<String> criterios = definirCriterios();
        String resultado = executarAnalise(cargoId, criterios);
        return criarRelatorio(resultado);

    }

    protected abstract List<String> definirCriterios();

    protected abstract String executarAnalise(UUID cargoId, List<String> criterios);

    protected abstract String criarRelatorio(String resultado);
}
