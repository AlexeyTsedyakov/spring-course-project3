package com.example.server.util;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorEntity {
    private final String message;
    private final LocalDateTime timestamp;

    public static String buildErrorMessage(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError error : fieldErrors) {
            errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
        }
        errorMessage.delete(errorMessage.length() - 2, errorMessage.length());

        return errorMessage.toString();
    }

    public ErrorEntity(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
