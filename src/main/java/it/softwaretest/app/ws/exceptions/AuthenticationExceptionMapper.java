package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    @Override
    public Response toResponse(AuthenticationException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), ErrorMessages.AUTHENTICATION_FAILED.name(), "http://www.softwaretest.it/faq");
        return Response.status(Response.Status.UNAUTHORIZED).entity(errorMessage).build();
    }

}
