package edu.bilkent.bilbilet.exception;

public class ExampleException extends BaseException {
    public ExampleException() {
        super("This is an example exception.");
    }

    public ExampleException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
