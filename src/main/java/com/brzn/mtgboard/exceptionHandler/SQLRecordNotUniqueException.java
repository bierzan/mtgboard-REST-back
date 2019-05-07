package com.brzn.mtgboard.exceptionHandler;

import org.springframework.web.HttpMediaTypeNotAcceptableException;

public class SQLRecordNotUniqueException extends HttpMediaTypeNotAcceptableException {
    public SQLRecordNotUniqueException(String errorMessage) {
        super(errorMessage);
    }
}