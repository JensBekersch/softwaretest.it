package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CouldNotUpdateRecordExceptionMapper implements ExceptionMapper<CouldNotUpdateRecordException> {

    private ErrorMessage errorMessage;

    @Inject
    public CouldNotUpdateRecordExceptionMapper(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Response toResponse(CouldNotUpdateRecordException exception) {
        this.createErrorMessage(exception);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }

    private void createErrorMessage(CouldNotUpdateRecordException exception) {
        this.errorMessage.setErrorMessage(exception.getMessage());
        this.errorMessage.setErrorMessageKey(ErrorMessageDefinitions.COULD_NOT_UPDATE_RECORD.name());
        this.errorMessage.setHref("http://www.softwaretest.it/faq");
    }
}
