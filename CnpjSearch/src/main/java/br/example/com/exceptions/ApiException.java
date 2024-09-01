package br.example.com.exceptions;

// Exceção personalizada para a API
public class ApiException extends Exception{
    public ApiException(String message) {
        super(message);
    }
}
