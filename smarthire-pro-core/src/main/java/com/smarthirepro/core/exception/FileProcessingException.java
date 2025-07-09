package com.smarthirepro.core.exception;

import org.springframework.http.HttpStatus;

public class FileProcessingException extends FrameworkBaseException {
  public FileProcessingException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}