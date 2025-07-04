package com.smarthirepro.api.models;

import java.util.List;

import com.smarthirepro.domain.model.CargoGenerico;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "cargo")
public class Cargo extends CargoGenerico {

    private List<String> fases;

    @DecimalMin("0.0")
    @DecimalMax("1.0")
    @Column(name = "pontuacao_minima")
    private Double pontuacaoMinima;

}
