package com.example.loyaltysystem.exceptions;

public class CustomException extends RuntimeException {
    private final String status;

    public CustomException(String message, String status) {
        super(message);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
