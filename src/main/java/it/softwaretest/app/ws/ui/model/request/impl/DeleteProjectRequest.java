package it.softwaretest.app.ws.ui.model.request.impl;

import it.softwaretest.app.ws.ui.model.request.DeleteProjectRequestInterface;

public class DeleteProjectRequest implements DeleteProjectRequestInterface {

    private String userId;
    private long projectId;

    public DeleteProjectRequest() {}

    public DeleteProjectRequest(String userId, long projectId) {
        this.userId = userId;
        this.projectId = projectId;
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

}
