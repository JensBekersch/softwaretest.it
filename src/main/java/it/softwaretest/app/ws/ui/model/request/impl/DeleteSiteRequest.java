package it.softwaretest.app.ws.ui.model.request.impl;

import it.softwaretest.app.ws.ui.model.request.DeleteSiteRequestInterface;

public class DeleteSiteRequest implements DeleteSiteRequestInterface {

    private String userId;
    private long id;

    public DeleteSiteRequest() {}

    public DeleteSiteRequest(String userId, long id) {
        this.userId = userId;
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
