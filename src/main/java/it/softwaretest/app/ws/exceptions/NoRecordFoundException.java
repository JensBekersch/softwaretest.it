package it.softwaretest.app.ws.exceptions;

public class NoRecordFoundException extends RuntimeException {

    private static final long serialVersionUID = 5214052627713299306L;

    public NoRecordFoundException(String message) {
        super(message);
    }
}
