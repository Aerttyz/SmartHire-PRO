package com.smarthirepro.core.exception;

import org.springframework.http.HttpStatus;

public class BusinessRuleException extends BusinessBaseException {
    public BusinessRuleException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}