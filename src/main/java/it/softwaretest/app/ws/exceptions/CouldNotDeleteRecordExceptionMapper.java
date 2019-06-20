package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CouldNotDeleteRecordExceptionMapper implements ExceptionMapper<CouldNotDeleteRecordException> {

    private ErrorMessage errorMessage;

    @Inject
    public CouldNotDeleteRecordExceptionMapper(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Response toResponse(CouldNotDeleteRecordException exception) {
        this.createErrorMessage(exception);

        return Response.status(Response.Status.CONFLICT).entity(errorMessage).build();
    }

    private void createErrorMessage(CouldNotDeleteRecordException exception) {
        this.errorMessage.setErrorMessage(exception.getMessage());
        this.errorMessage.setErrorMessageKey(ErrorMessageDefinitions.COULD_NOT_DELETE_RECORD.name());
        this.errorMessage.setHref("http://www.softwaretest.it/faq");
    }
}
