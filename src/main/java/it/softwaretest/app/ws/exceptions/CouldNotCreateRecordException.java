package it.softwaretest.app.ws.exceptions;

public class CouldNotCreateRecordException extends RuntimeException {

    private static final long serialVersionUID = 479563721678919363L;

    public CouldNotCreateRecordException(String message) {
        super(message);
    }

}
