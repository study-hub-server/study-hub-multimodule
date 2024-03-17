package com.example.webclient.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionMessage> handle(CustomException e) {
        int statusCode = e.getStatus().getStatusCode();
        return ResponseEntity.status(HttpStatus.valueOf(statusCode))
                .body(ExceptionMessage.of(e.getStatus(), e.getMessage()));
    }
}
