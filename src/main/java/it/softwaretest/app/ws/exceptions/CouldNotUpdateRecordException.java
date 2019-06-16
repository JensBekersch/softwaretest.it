package it.softwaretest.app.ws.exceptions;

public class CouldNotUpdateRecordException extends RuntimeException {

    private static final long serialVersionUID = -5170758074865104735L;

    public CouldNotUpdateRecordException(String message) {
        super(message);
    }

}
