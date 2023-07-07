package com.example.demoapp.exceptions;

public class AccountProcessingException extends RuntimeException {
    public AccountProcessingException(String message) {
        super(message);
    }
}
