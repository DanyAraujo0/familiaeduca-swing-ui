package br.com.familiaeduca.ui.service.exception;

public class ApiUnauthorizedException extends RuntimeException {
    public ApiUnauthorizedException(String message) {
        super(message);
    }
}
