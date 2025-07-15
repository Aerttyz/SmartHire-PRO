package com.smarthirepro.core.infra;

import com.smarthirepro.core.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(FrameworkBaseException.class)
    public ResponseEntity<BaseErrorMessage> handleFrameworkExceptions(FrameworkBaseException ex) {
        logger.error("Exceção do Framework: {}", ex.getMessage());
        BaseErrorMessage response = new BaseErrorMessage(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorMessage> validationHandler(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                .findFirst()
                .orElse("Erro de validação nos dados enviados.");

        logger.warn("Erro de validação: {}", message);
        BaseErrorMessage response = new BaseErrorMessage(HttpStatus.BAD_REQUEST, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<BaseErrorMessage> methodNotSupportedHandler(HttpRequestMethodNotSupportedException ex) {
        logger.warn("Método HTTP não suportado: {}", ex.getMethod());
        BaseErrorMessage response = new BaseErrorMessage(HttpStatus.METHOD_NOT_ALLOWED, "Método HTTP não permitido.");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }

    @ExceptionHandler({ExpiredJwtException.class, MalformedJwtException.class, UnsupportedJwtException.class})
    public ResponseEntity<BaseErrorMessage> jwtExceptionHandler(Exception ex) {
        logger.error("Erro de Token JWT: {}", ex.getMessage());
        BaseErrorMessage response = new BaseErrorMessage(HttpStatus.UNAUTHORIZED, "Token inválido, expirado ou malformado.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseErrorMessage> illegalArgumentHandler(IllegalArgumentException ex) {
        logger.error("Erro de argumento inválido: ", ex);
        BaseErrorMessage treatedResponse = new BaseErrorMessage(HttpStatus.BAD_REQUEST, "Requisição inválida. Verifique os parâmetros informados.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(treatedResponse);
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<BaseErrorMessage> mailSenderHandler(MailException ex) {
        logger.error("Erro ao enviar email: ", ex);
        BaseErrorMessage response = new BaseErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Erro no serviço de envio de email.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorMessage> genericExceptionHandler(Exception ex) {
        logger.error("Erro inesperado no servidor: ", ex);
        BaseErrorMessage response = new BaseErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro inesperado no servidor."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}