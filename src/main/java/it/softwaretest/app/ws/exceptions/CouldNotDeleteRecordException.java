package it.softwaretest.app.ws.exceptions;

public class CouldNotDeleteRecordException extends RuntimeException {

    private static final long serialVersionUID = -1764897453454747220L;

    public CouldNotDeleteRecordException(String message) {
        super(message);
    }

}
