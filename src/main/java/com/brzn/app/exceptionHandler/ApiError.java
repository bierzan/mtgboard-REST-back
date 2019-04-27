package com.brzn.app.exceptionHandler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class ApiError {
 
    private HttpStatus status;
    private String message;
    private String timestamp = LocalDateTime.now().toString();
    private List<String> errors;
 
    protected ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
 
    protected ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }
}