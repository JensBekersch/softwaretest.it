package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    private ErrorMessage errorMessage;

    @Inject
    public AuthenticationExceptionMapper(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Response toResponse(AuthenticationException exception) {
        this.createErrorMessage(exception);

        return Response.status(Response.Status.UNAUTHORIZED).entity(errorMessage).build();
    }

    private void createErrorMessage(AuthenticationException exception) {
        this.errorMessage.setErrorMessage(exception.getMessage());
        this.errorMessage.setErrorMessageKey(ErrorMessageDefinitions.AUTHENTICATION_FAILED.name());
        this.errorMessage.setHref("http://www.softwaretest.it/faq");
    }

}