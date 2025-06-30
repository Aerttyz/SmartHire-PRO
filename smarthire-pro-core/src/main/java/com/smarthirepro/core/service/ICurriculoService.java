package com.smarthirepro.core.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ICurriculoService {

  public String pegarCaminhoDoCurriculo(MultipartFile file, UUID vagaId);
}
