package it.softwaretest.app.ws.ui.model.response;

import it.softwaretest.app.ws.ui.model.response.impl.RequestOperation;
import it.softwaretest.app.ws.ui.model.response.impl.ResponseStatus;

public interface DeleteUserProfileResponseInterface {

    RequestOperation getRequestOperation();

    void setRequestOperation(RequestOperation requestOperation);

    ResponseStatus getResponseStatus();

    void setResponseStatus(ResponseStatus responseStatus);

}
