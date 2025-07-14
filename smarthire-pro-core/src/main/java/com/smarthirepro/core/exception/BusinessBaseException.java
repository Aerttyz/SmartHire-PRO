package com.smarthirepro.core.exception;

import org.springframework.http.HttpStatus;

public abstract class BusinessBaseException extends FrameworkBaseException {
    public BusinessBaseException(String message, HttpStatus status) {
        super(message, status);
    }
}
