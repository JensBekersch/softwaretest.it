package it.softwaretest.app.ws.ui.model.response.impl;

import it.softwaretest.app.ws.ui.model.response.ErrorMessageDefinitionsInterface;

public enum ErrorMessageDefinitions implements ErrorMessageDefinitionsInterface {

    MISSING_REQUIRED_FIELD("Bitte geben Sie einen Wert an!"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    NO_RECORD_FOUND("No Record found"),
    AUTHENTICATION_FAILED("Authentication failed!"),
    COULD_NOT_UPDATE_RECORD("Could not update record!"),
    COULD_NOT_DELETE_RECORD("Could not delete record!"),
    RECORD_ALREADY_EXISITS("EIntrag exisitiert bereits");

    private String errorMessage;

    ErrorMessageDefinitions(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
