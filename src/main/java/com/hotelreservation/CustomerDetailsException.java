package com.hotelreservation;

public class CustomerDetailsException extends Exception {
    private static final long serialVersionUID = 1L;

    enum ExceptionType {
        ENTERED_NULL, ENTERED_EMPTY, ENTERED_INVALID
    }

    ExceptionType exceptionType;

    public CustomerDetailsException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }
}
