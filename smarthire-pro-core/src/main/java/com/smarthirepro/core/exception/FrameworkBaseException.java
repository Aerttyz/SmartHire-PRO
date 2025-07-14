package com.smarthirepro.core.exception;

import org.springframework.http.HttpStatus;

public abstract class FrameworkBaseException extends RuntimeException {
  private final HttpStatus status;

  public FrameworkBaseException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}