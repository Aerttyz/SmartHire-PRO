package com.smarthirepro.core.infra;

import org.springframework.http.HttpStatus;


public class FrameworkErrorMessage {
    private HttpStatus status;
    private String message;

    public FrameworkErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
