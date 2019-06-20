package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoRecordFoundExceptionMapper implements ExceptionMapper<NoRecordFoundException> {

    private ErrorMessage errorMessage;

    @Inject
    public NoRecordFoundExceptionMapper(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Response toResponse(NoRecordFoundException exception) {
        this.createErrorMessage(exception);

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }

    private void createErrorMessage(NoRecordFoundException exception) {
        this.errorMessage.setErrorMessage(exception.getMessage());
        this.errorMessage.setErrorMessageKey(ErrorMessageDefinitions.NO_RECORD_FOUND.name());
        this.errorMessage.setHref("http://www.softwaretest.it/faq");
    }

}
