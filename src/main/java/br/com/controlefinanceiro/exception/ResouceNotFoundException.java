package br.com.controlefinanceiro.exception;

public class ResouceNotFoundException extends RuntimeException {
    public ResouceNotFoundException(String message) {
        super(message);
    }
}
