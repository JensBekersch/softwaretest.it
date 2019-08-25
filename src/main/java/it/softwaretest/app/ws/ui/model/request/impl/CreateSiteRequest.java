package it.softwaretest.app.ws.ui.model.request.impl;

public class CreateSiteRequest {

    private String userId;
    private long project_id;
    private String name;
    private String url;

    public CreateSiteRequest() {}

    public CreateSiteRequest(String userId, long project_id, String name, String url) {
        this.userId = userId;
        this.project_id = project_id;
        this.name = name;
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
