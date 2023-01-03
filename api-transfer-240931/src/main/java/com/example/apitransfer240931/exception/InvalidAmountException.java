package com.example.apitransfer240931.exception;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException() {
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}


