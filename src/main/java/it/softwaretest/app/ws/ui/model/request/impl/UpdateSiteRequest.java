package it.softwaretest.app.ws.ui.model.request.impl;

public class UpdateSiteRequest {

    private long id;
    private String userId;
    private String newName;

    public UpdateSiteRequest() {}

    public UpdateSiteRequest(long id, String userId, String newName) {
        this.id = id;
        this.userId = userId;
        this.newName = newName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
