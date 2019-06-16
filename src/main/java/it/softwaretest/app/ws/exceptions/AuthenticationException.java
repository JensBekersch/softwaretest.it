package it.softwaretest.app.ws.exceptions;

public class AuthenticationException  extends RuntimeException {

    private static final long serialVersionUID = -2128168597283678652L;

    public AuthenticationException(String message) {
        super(message);
    }

}
