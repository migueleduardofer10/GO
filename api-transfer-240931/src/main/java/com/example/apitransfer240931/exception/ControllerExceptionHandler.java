package com.example.apitransfer240931.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage AccountNotFoundException(AccountNotFoundException ex, WebRequest request){
        ErrorMessage message=new ErrorMessage(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }


    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage InsufficientBalanceException(InsufficientBalanceException ex, WebRequest request){
        ErrorMessage message=new ErrorMessage(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }

    @ExceptionHandler(InvalidAmountException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage InvalidAmountException(InvalidAmountException ex, WebRequest request){
        ErrorMessage message=new ErrorMessage(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }

    @ExceptionHandler(InvalidCurrencyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage InvalidCurrencyException(InvalidCurrencyException ex, WebRequest request){
        ErrorMessage message=new ErrorMessage(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }


}