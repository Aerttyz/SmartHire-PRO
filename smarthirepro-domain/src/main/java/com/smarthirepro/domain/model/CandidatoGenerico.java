package com.smarthirepro.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidato")
public class CandidatoGenerico {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;

  private String nome;

  private String email;

  private String telefone;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "curriculo_id")
  private CurriculoGenerico curriculo;

  @ManyToOne
  @JoinColumn(name = "vaga_id")
  private VagaGenerica vaga;

  public void updateEmail(String novoEmail) {
    this.email = novoEmail;
  }

}
