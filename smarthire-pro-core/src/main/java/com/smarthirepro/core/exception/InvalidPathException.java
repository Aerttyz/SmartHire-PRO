package com.smarthirepro.core.exception;

import org.springframework.http.HttpStatus;

public class InvalidPathException extends FrameworkBaseException {
    public InvalidPathException() {
        super("O caminho fornecido é inválido.", HttpStatus.BAD_REQUEST);
    }
}