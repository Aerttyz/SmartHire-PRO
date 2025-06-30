package com.smarthirepro.core.service;

import com.smarthirepro.core.dto.AcessDto;
import com.smarthirepro.core.dto.AuthDto;
import com.smarthirepro.domain.model.Empresa;

public interface IAuthService {
  AcessDto login(AuthDto authDto);
  Empresa salvar(Empresa empresa);
}
