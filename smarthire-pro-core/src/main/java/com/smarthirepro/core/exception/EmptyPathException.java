package com.smarthirepro.core.exception;

import org.springframework.http.HttpStatus;

public class EmptyPathException extends FrameworkBaseException {
    public EmptyPathException() {
        super("O caminho é obrigatório.", HttpStatus.BAD_REQUEST);
    }
}