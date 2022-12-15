package com.springboot.auth.exception;

public class ResourceAlreadyExistsException extends RuntimeException{
    private String message;

    public ResourceAlreadyExistsException() {
    }

    public ResourceAlreadyExistsException(String message) {
        this.message = message;
    }
}
