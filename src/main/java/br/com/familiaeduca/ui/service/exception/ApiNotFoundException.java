package br.com.familiaeduca.ui.service.exception;

public class ApiNotFoundException extends RuntimeException {
    public ApiNotFoundException(String message) {
        super(message);
    }
}
