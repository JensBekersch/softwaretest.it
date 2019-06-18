package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoRecordFoundExceptionMapper implements ExceptionMapper<NoRecordFoundException> {

        @Override
        public Response toResponse(NoRecordFoundException exception) {
            ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), ErrorMessageDefinitions.NO_RECORD_FOUND.name(), "http://www.softwaretest.it/faq");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
        }

}
