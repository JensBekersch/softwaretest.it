package it.softwaretest.app.ws.ui.model.request.impl;

import it.softwaretest.app.ws.ui.model.request.CreateProjectRequestInterface;

public class CreateProjectRequest implements CreateProjectRequestInterface {

    private String userId;
    private String name;

    public CreateProjectRequest() {}

    public CreateProjectRequest(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
