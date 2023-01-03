package com.example.apitransfer240931.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    LocalDateTime time;
    String message;
    String description;
}
