package it.softwaretest.app.ws.ui.model.request.impl;

import it.softwaretest.app.ws.ui.model.request.GetSiteRequestInterface;

public class GetSiteRequest implements GetSiteRequestInterface {

    private boolean list;
    private long id;

    public  GetSiteRequest() {}

    public GetSiteRequest(boolean list, long id) {
        this.list = list;
        this.id = id;
    }

    public boolean getList() {
        return list;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
