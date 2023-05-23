package edu.bilkent.bilbilet.exception;

public class BaseException extends Exception {
    public BaseException() {
        super("No exception message provided.");
    }

    public BaseException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
