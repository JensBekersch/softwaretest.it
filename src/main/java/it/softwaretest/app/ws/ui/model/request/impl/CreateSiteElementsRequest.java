package it.softwaretest.app.ws.ui.model.request.impl;

import it.softwaretest.app.ws.ui.model.request.CreateSiteElementsRequestInterface;

public class CreateSiteElementsRequest implements CreateSiteElementsRequestInterface {

    private long siteId;
    private String userId;

    public CreateSiteElementsRequest() {}

    public CreateSiteElementsRequest(long siteId, String userId) {
        this.siteId = siteId;
        this.userId = userId;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
