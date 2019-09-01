package it.softwaretest.app.ws.ui.model.response.impl;

import it.softwaretest.app.ws.io.entity.impl.SingleDomElementEntity;
import it.softwaretest.app.ws.ui.model.response.SiteElementsInterface;

import java.util.List;

public class SiteElements implements SiteElementsInterface {

    private long id;
    private long siteId;
    private List<SingleDomElementEntity> singleDomElements;

    public SiteElements() {}

    public SiteElements(long id, long siteId, List<SingleDomElementEntity> singleDomElements) {
        this.id = id;
        this.siteId = siteId;
        this.singleDomElements = singleDomElements;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public List<SingleDomElementEntity> getSingleDomElements() {
        return singleDomElements;
    }

    public void setSingleDomElements(List<SingleDomElementEntity> singleDomElements) {
        this.singleDomElements = singleDomElements;
    }

}
