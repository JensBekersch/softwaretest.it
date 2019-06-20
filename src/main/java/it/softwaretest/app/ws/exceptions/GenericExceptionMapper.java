package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    private ErrorMessage errorMessage;

    @Inject
    public GenericExceptionMapper(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Response toResponse(Throwable exception) {
        this.createErrorMessage(exception);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }

    private void createErrorMessage(Throwable exception) {
        this.errorMessage.setErrorMessage(exception.getMessage());
        this.errorMessage.setErrorMessageKey(ErrorMessageDefinitions.INTERNAL_SERVER_ERROR.name());
        this.errorMessage.setHref("http://www.softwaretest.it/faq");
    }

}
