package com.smarthirepro.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curriculo {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;

  private String nome;

  private String email;

  private String telefone;

  private String experiencia;

  @ElementCollection
  private List<String> formacaoAcademica = new ArrayList<>();

  @ElementCollection
  private List<String> habilidades = new ArrayList<>();

  @ElementCollection
  private List<String> idiomas = new ArrayList<>();

}
