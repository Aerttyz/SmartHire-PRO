package com.smarthirepro.core.exception;

import org.springframework.http.HttpStatus;

public class PersistenceException extends FrameworkBaseException {
    public PersistenceException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}