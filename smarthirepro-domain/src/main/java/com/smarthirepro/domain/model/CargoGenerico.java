package com.smarthirepro.domain.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CargoGenerico {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "empresa_id")
  private Empresa empresa;

  private String nome;

  private boolean isActive;

  @OneToOne(mappedBy = "cargo", cascade = CascadeType.ALL)
  private CargoCompetenciasGenerico requisitos;

  @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
  private List<Candidato> candidatos;

}
