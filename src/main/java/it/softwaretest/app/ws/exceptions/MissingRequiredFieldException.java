package it.softwaretest.app.ws.exceptions;

public class MissingRequiredFieldException extends RuntimeException {

    private static final long serialVersionUID = -1091420689502987880L;

    public MissingRequiredFieldException(String message) {
        super(message);
    }

}
