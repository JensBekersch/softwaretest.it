package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CouldNotUpdateRecordExceptionMapper implements ExceptionMapper<CouldNotUpdateRecordException> {

    @Override
    public Response toResponse(CouldNotUpdateRecordException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), ErrorMessages.COULD_NOT_UPDATE_RECORD.name(), "http://www.softwaretest.it/faq");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }
}
