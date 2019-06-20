package it.softwaretest.app.ws.exceptions;

import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessage;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CouldNotCreateRecordExceptionMapper implements ExceptionMapper<CouldNotCreateRecordException> {

    private ErrorMessage errorMessage;

    @Inject
    public CouldNotCreateRecordExceptionMapper(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Response toResponse(CouldNotCreateRecordException exception) {
        this.createErrorMessage(exception);

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }

    private void createErrorMessage(CouldNotCreateRecordException exception) {
        this.errorMessage.setErrorMessage(exception.getMessage());
        this.errorMessage.setErrorMessageKey(ErrorMessageDefinitions.RECORD_ALREADY_EXISITS.name());
        this.errorMessage.setHref("http://www.softwaretest.it/faq");
    }

}
