package com.smarthirepro.api.models;

import com.smarthirepro.domain.model.CargoCompetenciasGenerico;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargo_competencias")
public class CargoComp extends CargoCompetenciasGenerico {

    private Double pesoHabilidades;
    private Double pesoIdiomas;
    private Double pesoFormacaoAcademica;
    private Double pesoExperiencia;
}
