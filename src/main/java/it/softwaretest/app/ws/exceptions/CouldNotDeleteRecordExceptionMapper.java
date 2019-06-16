package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CouldNotDeleteRecordExceptionMapper implements ExceptionMapper<CouldNotDeleteRecordException> {

    @Override
    public Response toResponse(CouldNotDeleteRecordException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), ErrorMessages.COULD_NOT_DELETE_RECORD.name(), "http://www.softwaretest.it/faq");
        return Response.status(Response.Status.CONFLICT).entity(errorMessage).build();
    }
}
