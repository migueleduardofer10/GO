package com.example.apitransfer240931.exception;

public class InvalidCurrencyException extends RuntimeException{
    public InvalidCurrencyException() {
    }

    public InvalidCurrencyException(String message) {
        super(message);
    }
}

