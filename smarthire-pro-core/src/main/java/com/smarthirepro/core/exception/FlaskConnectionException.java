package com.smarthirepro.core.exception;

public class FlaskConnectionException extends RuntimeException {
    public FlaskConnectionException() {
        super("Conexão com o serviço Flask falhou");
    }

    public FlaskConnectionException(String message) {
        super(message);
    }
}
