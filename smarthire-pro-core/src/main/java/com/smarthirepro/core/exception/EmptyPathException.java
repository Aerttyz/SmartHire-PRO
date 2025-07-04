package com.smarthirepro.core.exception;

public class EmptyPathException extends RuntimeException {
    public EmptyPathException() {
        super("O caminho é obrigatório");
    }

    public EmptyPathException(String message) {
        super(message);
    }
}
