package com.smarthirepro.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="instancia", discriminatorType = DiscriminatorType.STRING)
@Table(name = "vagas_genericas")
public abstract class VagaGenerica {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name =  "empresa_id")
  private Empresa empresa;

  private String nome;

  private boolean isActive;

  // extrair classe abstrata de vaga requisitos model
  @OneToOne(mappedBy = "contexto", cascade = CascadeType.ALL)
  private VagaGenericaRequisitosModel requisitos;

  // candidato virará participante abstratamente
  @OneToMany(mappedBy = "contexto", cascade = CascadeType.ALL)
  private List<CandidatoGenerico> participantes;

  @DecimalMin("0.0")
  @DecimalMax("1.0")
  @Column(name = "pontuacao_minima")
  private Double pontuacaoMinima;


}
