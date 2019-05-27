package com.brzn.mtgboard.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;

public class WrongUsernameOrPasswordException extends HttpClientErrorException {

    public WrongUsernameOrPasswordException(HttpStatus statusCode) {
        super(statusCode);
    }

    public WrongUsernameOrPasswordException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public WrongUsernameOrPasswordException(HttpStatus statusCode, String statusText, byte[] body, Charset responseCharset) {
        super(statusCode, statusText, body, responseCharset);
    }

    public WrongUsernameOrPasswordException(HttpStatus statusCode, String statusText, HttpHeaders headers, byte[] body, Charset responseCharset) {
        super(statusCode, statusText, headers, body, responseCharset);
    }

}