package it.softwaretest.app.ws.ui.model.response.impl;

import it.softwaretest.app.ws.ui.model.response.DeleteUserProfileResponseInterface;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeleteUserProfileResponse implements DeleteUserProfileResponseInterface {

    private RequestOperation requestOperation;
    private ResponseStatus responseStatus;

    public RequestOperation getRequestOperation() {
        return requestOperation;
    }

    public void setRequestOperation(RequestOperation requestOperation) {
        this.requestOperation = requestOperation;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

}
