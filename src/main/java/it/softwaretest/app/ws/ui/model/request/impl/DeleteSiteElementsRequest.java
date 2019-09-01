package it.softwaretest.app.ws.ui.model.request.impl;

import it.softwaretest.app.ws.ui.model.request.DeleteSiteElementsRequestInterface;

public class DeleteSiteElementsRequest implements DeleteSiteElementsRequestInterface {

    private long siteId;

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

}
