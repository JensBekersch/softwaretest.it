package it.softwaretest.app.ws.ui.model.request;

public interface CreateSiteRequestInterface {

    void setUserId(String userId);

    long getProject_id();

    void setProject_id(long project_id);

    String getName();

    void setName(String name);

    String getUrl();

    void setUrl(String url);

}
