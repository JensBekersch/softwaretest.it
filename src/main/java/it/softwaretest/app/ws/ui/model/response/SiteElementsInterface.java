package it.softwaretest.app.ws.ui.model.response;

import it.softwaretest.app.ws.io.entity.impl.SingleDomElementEntity;

import java.util.List;

public interface SiteElementsInterface {
    long getId();
    void setId(long id);
    long getSiteId();
    void setSiteId(long siteId);
    List<SingleDomElementEntity> getSingleDomElements();
    void setSingleDomElements(List<SingleDomElementEntity> singleDomElements);
}
