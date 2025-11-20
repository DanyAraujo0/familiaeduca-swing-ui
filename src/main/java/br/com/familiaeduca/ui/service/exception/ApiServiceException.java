package br.com.familiaeduca.ui.service.exception;

public class ApiServiceException extends RuntimeException {
    public ApiServiceException(String message) {
        super(message);
    }

    public ApiServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}