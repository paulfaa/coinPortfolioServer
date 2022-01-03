package com.coinportfolio.server.exceptions;

public class ResponseJsonException extends Exception {
    public ResponseJsonException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}