package it.softwaretest.app.ws.ui.model.request;

public interface UpdateProjectRequestInterface {

    String getUserId();

    void setUserId(String userId);

    long getProjectId();

    void setProjectId(long projectId);

    String getNewName();

    void setNewName(String newName);
}
