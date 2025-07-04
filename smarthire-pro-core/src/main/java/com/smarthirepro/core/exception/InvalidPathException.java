package com.smarthirepro.core.exception;

public class InvalidPathException extends RuntimeException {
    public InvalidPathException() {
        super("O caminho passado é inválido");
    }

    public InvalidPathException(String message) {
        super(message);
    }
}
