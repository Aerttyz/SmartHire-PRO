package com.smarthirepro.core.service.impl;

import com.smarthirepro.core.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smarthirepro.core.dto.AcessDto;
import com.smarthirepro.core.dto.AuthDto;
import com.smarthirepro.core.security.jwt.JwtUtils;
import com.smarthirepro.domain.model.Empresa;
import com.smarthirepro.domain.repositories.EmpresaRepository;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private EmpresaRepository empresaRepository;

    public AcessDto login(AuthDto authDto) {
        try {
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authDto.email(),
                    authDto.senha());

            Authentication authentication = authenticationManager.authenticate(userAuth);

            Empresa userAuthenticate = (Empresa) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromEmpresa(userAuthenticate);

            AcessDto acessDto = new AcessDto(token);
            return acessDto;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao autenticar usuário: " + e.getMessage(), e);
        }
    }

    public Empresa salvar(Empresa empresa) {
        if (empresaRepository.findByCnpj(empresa.getCnpj()).isPresent()) {
            throw new RuntimeException("CNPJ já cadastrado no sistema.");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(empresa.getSenha());
        empresa.setSenha(senhaCriptografada);
        return empresaRepository.save(empresa);
    }
}
