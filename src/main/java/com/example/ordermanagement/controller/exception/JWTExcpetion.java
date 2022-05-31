package com.example.ordermanagement.controller.exception;

import org.springframework.http.HttpStatus;

public class JWTExcpetion extends RuntimeException {

    private HttpStatus status;
    private String message;

    public JWTExcpetion(HttpStatus status, String message) {
        this.status = status;
        this.message = JWTExcpetion.class.getSimpleName() + " - " + message;
    }

    public JWTExcpetion(String message, HttpStatus status, String message1) {
        super(JWTExcpetion.class.getSimpleName() + " - " + message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
