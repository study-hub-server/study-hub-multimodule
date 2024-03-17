package com.example.webclient.api.exception;

public abstract class CustomException extends RuntimeException {

    public abstract StatusType getStatus();

    public abstract String getMessage();

    public CustomException(String message) {
        super(message);
    }
}