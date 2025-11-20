package br.com.familiaeduca.ui.service.exception;

public class ApiBadRequestException extends RuntimeException {
    public ApiBadRequestException(String message) {
        super(message);
    }
}
