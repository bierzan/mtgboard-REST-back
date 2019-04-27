package com.brzn.app.exceptionHandler;

import org.springframework.web.HttpMediaTypeNotAcceptableException;

public class SQLRecordNotUniqueException extends HttpMediaTypeNotAcceptableException {
    public SQLRecordNotUniqueException(String errorMessage) {
        super(errorMessage);
    }
}