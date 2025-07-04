package com.smarthirepro.core.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.smarthirepro.core.exception.BusinessRuleException;
import com.smarthirepro.core.exception.EmptyPathException;
import com.smarthirepro.core.exception.FileProcessingException;
import com.smarthirepro.core.exception.FlaskConnectionException;
import com.smarthirepro.core.exception.InvalidPathException;
import com.smarthirepro.domain.model.Curriculo;
import com.smarthirepro.domain.repositories.CurriculoRepository;

import jakarta.persistence.PersistenceException;

@Service
public class CurriculoService {
  private final RestTemplate restTemplate = new RestTemplate();
  private final String flaskUrl = "http://localhost:5000/extract_entities";

  @Autowired
  private CurriculoRepository curriculoRepository;

  @Autowired
  private CandidatoService candidatoService;

  public Map<String, Object> extrairEntidades(String path) {
    if (path == null || path.isEmpty()) {
      throw new EmptyPathException();
    }

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, String> payload = Map.of("path", path);
    HttpEntity<Map<String, String>> entity = new HttpEntity<>(payload, headers);

    try {
      ResponseEntity<Map> response = restTemplate.postForEntity(flaskUrl, entity, Map.class);
      return response.getBody();
    } catch (HttpClientErrorException ex) {
      if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
        throw new InvalidPathException();
      }
      throw new FlaskConnectionException("Erro inesperado no serviço Flask. Tente novamente mais tarde");
    } catch (ResourceAccessException ex) {
      throw new FlaskConnectionException();
    }
  }

  public List<Curriculo> salvarCurriculo(String pasta, UUID idVaga) {
    Map<String, Object> resultado = extrairEntidades(pasta);
    Map<String, Object> mapaDeEntidades = (Map<String, Object>) resultado.get("entities");

    List<Curriculo> curriculosSalvos = new ArrayList<>();

    for (Map.Entry<String, Object> entry : mapaDeEntidades.entrySet()) {
      Map<String, Object> curriculoData = (Map<String, Object>) entry.getValue();
      List<List<String>> entidades = (List<List<String>>) curriculoData.get("entities");

      Curriculo curriculo = new Curriculo();

      if (curriculo.getHabilidades() == null)
        curriculo.setHabilidades(new ArrayList<>());
      if (curriculo.getIdiomas() == null)
        curriculo.setIdiomas(new ArrayList<>());

      for (List<String> entidade : entidades) {
        if (entidade.size() < 2)
          continue;

        String valor = entidade.get(0).trim();
        String tipo = entidade.get(1).toUpperCase(Locale.ROOT);

        switch (tipo) {
          case "NAME":
            if (curriculo.getNome() == null)
              curriculo.setNome(valor);
            break;
          case "EMAIL":
            if (curriculo.getEmail() == null)
              curriculo.setEmail(valor);
            break;
          case "PHONE":
            if (curriculo.getTelefone() == null)
              curriculo.setTelefone(valor);
            break;
          case "SKILLS":
            curriculo.getHabilidades().add(valor);
            break;
          case "LANGUAGES":
            curriculo.getIdiomas().add(valor);
            break;
          case "EDUCATION":
            curriculo.getFormacaoAcademica().add(valor);
            break;
          default:
            throw new BusinessRuleException("Tipo inválido: " + tipo);
        }
      }

      String responseStr = (String) curriculoData.get("response");
      try {
        String jsonLimpo = responseStr.replace("```json\n", "").replace("\n```", "");

        curriculo.setExperiencia(jsonLimpo);
      } catch (Exception e) {
        throw new PersistenceException("Erro ao salvar currículo");
      }

      curriculoRepository.save(curriculo);
      curriculosSalvos.add(curriculo);
      candidatoService.criarComCurriculo(curriculo, idVaga);

    }

    return curriculosSalvos;
  }

  public String pegarCaminhoDoCurriculo(MultipartFile file, UUID cargoId) {
    if (file == null || file.isEmpty()) {
      throw new EmptyPathException();
    }

    try {
      Path sharedDir = Path.of("C:\\Users\\alesa\\OneDrive\\Documentos\\Programas");
      if (!Files.exists(sharedDir)) {
        Files.createDirectories(sharedDir);
      }
      Path tempDir = Files.createDirectory(sharedDir.resolve("curriculos_" + cargoId + "_" + UUID.randomUUID()));
      Path caminho = tempDir.resolve(file.getOriginalFilename());
      Files.copy(file.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);

      try (ZipInputStream zis = new ZipInputStream(new FileInputStream(caminho.toFile()))) {
        ZipEntry zipEntry;
        while ((zipEntry = zis.getNextEntry()) != null) {
          Path novoCaminho = tempDir.resolve(zipEntry.getName());
          if (zipEntry.isDirectory()) {
            Files.createDirectories(novoCaminho);
          } else {
            Files.createDirectories(novoCaminho.getParent());
            try (OutputStream os = Files.newOutputStream(novoCaminho)) {
              zis.transferTo(os);
            }
          }
          zis.closeEntry();
        }
      }

      String nomeOriginal = file.getOriginalFilename();
      if (nomeOriginal != null && nomeOriginal.toLowerCase().endsWith(".zip")) {
        nomeOriginal = nomeOriginal.replaceFirst("(?i)\\.zip$", "");
      }
      System.out.println("Currículo extraído para: " + tempDir.toString());
      return tempDir.toString();
    } catch (IOException e) {
      throw new FileProcessingException("Erro ao processar arquivos");
    }
  }
}
