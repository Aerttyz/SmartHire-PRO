package com.smarthirepro.core.exception;

import org.springframework.http.HttpStatus;

public class FlaskConnectionException extends FrameworkBaseException {
    public FlaskConnectionException() {
        super("Falha na comunicação com o serviço de análise.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public FlaskConnectionException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}