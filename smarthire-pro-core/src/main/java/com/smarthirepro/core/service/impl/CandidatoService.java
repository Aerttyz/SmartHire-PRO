package com.smarthirepro.core.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthirepro.core.exception.BusinessRuleException;
import com.smarthirepro.core.service.ICargoService;
import com.smarthirepro.domain.model.Candidato;
import com.smarthirepro.domain.model.CargoGenerico;
import com.smarthirepro.domain.model.Curriculo;
import com.smarthirepro.domain.repositories.CandidatoRepository;

@Service
public class CandidatoService {

    @Autowired
    private ICargoService cargoService;

    @Autowired
    private CandidatoRepository candidatoRepository;

    public void criarComCurriculo(Curriculo curriculo, UUID idCargo) {
        Candidato candidato = new Candidato();
        if (curriculo == null) {
            throw new BusinessRuleException("Currículo não pode ser nulo.");
        }
        candidato.setCurriculo(curriculo);
        candidato.setNome(curriculo.getNome());
        candidato.setEmail(curriculo.getEmail());
        candidato.setTelefone(curriculo.getTelefone());

        CargoGenerico cargo = cargoService.listarPorId(idCargo);
        verificarEmailEmUso(candidato.getEmail(), candidato);
        candidato.setCargo(cargo);
        candidatoRepository.save(candidato);

    }

    private void verificarEmailEmUso(String email, Candidato candidato) {
        boolean emailEmUso = candidatoRepository.findByEmail(candidato.getEmail())
                .filter(e -> !e.equals(candidato))
                .isPresent();
        if (emailEmUso) {
            throw new BusinessRuleException("E-mail já cadastrado no sistema.");
        }
    }

}
