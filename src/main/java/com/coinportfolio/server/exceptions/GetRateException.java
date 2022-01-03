package com.coinportfolio.server.exceptions;

public class GetRateException extends Exception {
    public GetRateException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
