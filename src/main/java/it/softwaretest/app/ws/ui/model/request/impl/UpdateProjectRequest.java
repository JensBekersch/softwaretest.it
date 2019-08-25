package it.softwaretest.app.ws.ui.model.request.impl;

import it.softwaretest.app.ws.ui.model.request.UpdateProjectRequestInterface;

public class UpdateProjectRequest implements UpdateProjectRequestInterface {

    private String userId;
    private long projectId;
    private String newName;

    public UpdateProjectRequest() {}

    public UpdateProjectRequest(String userId, long projectId, String newName) {
        this.userId = userId;
        this.projectId = projectId;
        this.newName = newName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

}
