package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CouldNotDeleteRecordExceptionMapper implements ExceptionMapper<CouldNotDeleteRecordException> {

    @Override
    public Response toResponse(CouldNotDeleteRecordException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), ErrorMessageDefinitions.COULD_NOT_DELETE_RECORD.name(), "http://www.softwaretest.it/faq");
        return Response.status(Response.Status.CONFLICT).entity(errorMessage).build();
    }
}
