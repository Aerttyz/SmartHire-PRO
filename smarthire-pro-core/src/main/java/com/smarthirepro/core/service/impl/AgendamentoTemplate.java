package com.smarthirepro.core.service.impl;

import java.util.UUID;

public abstract class AgendamentoTemplate<T> {

  public final String runAgendamento(UUID cargoId, UUID candidatoId) {
    T dadosRelatorio = buscarDadosRelatorio(cargoId);
    return agendarReuniao(dadosRelatorio, candidatoId);
  }

  protected abstract T buscarDadosRelatorio(UUID cargoId); // PDI

  protected abstract String agendarReuniao(T dadosPDI, UUID candidatoId);
}
