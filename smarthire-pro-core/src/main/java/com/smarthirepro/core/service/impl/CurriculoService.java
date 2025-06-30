package com.smarthirepro.core.service.impl;

import com.smarthirepro.core.exception.EmptyPathException;
import com.smarthirepro.core.exception.FileProcessingException;
import com.smarthirepro.core.service.ICurriculoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class CurriculoService implements ICurriculoService {
  private final RestTemplate restTemplate = new RestTemplate();
  private final String flaskUrl = "http://localhost:5000/extract_entities";

  public String pegarCaminhoDoCurriculo(MultipartFile file, UUID vagaId) {
    if (file == null || file.isEmpty()) {
      throw new EmptyPathException();
    }

    try {
      Path tempDir = Files.createTempDirectory("curriculos_" + vagaId + "_" + UUID.randomUUID())
        .toAbsolutePath();

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
            try (OutputStream os = Files.newOutputStream(novoCaminho)){
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
      System.out.println("Currículo extraído para: " + tempDir.toString() + "\\" + nomeOriginal);
      return tempDir.toString() + "\\" + nomeOriginal;
    } catch(IOException e) {
      throw new FileProcessingException("Erro ao processar arquivos");
    }
  }

}
