package com.smarthirepro.domain.model;

import java.util.UUID;

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

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidato")
public class Candidato {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;

  private String nome;

  private String email;

  private String telefone;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "curriculo_id")
  private Curriculo curriculo;

  @ManyToOne
  @JoinColumn(name = "cargo_id")
  private CargoGenerico cargo;

  public void updateEmail(String novoEmail) {
    this.email = novoEmail;
  }

}
