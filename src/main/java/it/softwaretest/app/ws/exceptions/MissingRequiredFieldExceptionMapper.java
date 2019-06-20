package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MissingRequiredFieldExceptionMapper implements ExceptionMapper<MissingRequiredFieldException> {

    private ErrorMessage errorMessage;

    @Inject
    public MissingRequiredFieldExceptionMapper(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Response toResponse(MissingRequiredFieldException exception) {
        this.createErrorMessage(exception);

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }

    private void createErrorMessage(MissingRequiredFieldException exception) {
        this.errorMessage.setErrorMessage(exception.getMessage());
        this.errorMessage.setErrorMessageKey(ErrorMessageDefinitions.MISSING_REQUIRED_FIELD.name());
        this.errorMessage.setHref("http://www.softwaretest.it/faq");
    }

}
