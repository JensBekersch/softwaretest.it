package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CouldNotUpdateRecordExceptionMapper implements ExceptionMapper<CouldNotUpdateRecordException> {

    @Override
    public Response toResponse(CouldNotUpdateRecordException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), ErrorMessageDefinitions.COULD_NOT_UPDATE_RECORD.name(), "http://www.softwaretest.it/faq");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }
}
