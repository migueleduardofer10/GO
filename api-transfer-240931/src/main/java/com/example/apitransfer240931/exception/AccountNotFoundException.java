package com.example.apitransfer240931.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
