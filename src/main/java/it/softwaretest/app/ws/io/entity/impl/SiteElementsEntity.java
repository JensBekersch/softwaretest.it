package it.softwaretest.app.ws.io.entity.impl;

import it.softwaretest.app.ws.io.entity.SiteElementsEntityInferface;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "SiteElements")
public class SiteElementsEntity implements SiteElementsEntityInferface, Serializable {

    private static final long serialVersionUID = -2991370828211334423L;

    @Id
    @GeneratedValue
    private long id;

    private long siteId;

    @OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<SingleDomElementEntity> singleDomElements;

    public SiteElementsEntity() {}

    public SiteElementsEntity(long siteId, List<SingleDomElementEntity> singleDomElements) {
        this.siteId = siteId;
        this.singleDomElements = singleDomElements;
    }

    public long getId() {
        return id;
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
