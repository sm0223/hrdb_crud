package com.shux.hms.CustomExceptions;

public class CustomException extends RuntimeException{
    private int statusCode;
    public CustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public CustomException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
