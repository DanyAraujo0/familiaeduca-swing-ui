package br.com.familiaeduca.ui.service.exception;

public class ApiConnectionException extends ApiServiceException {
    public ApiConnectionException(String message) {
        super(message);
    }

    public ApiConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
