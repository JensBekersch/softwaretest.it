package it.softwaretest.app.ws.site.builder.siteListBuilder;

import it.softwaretest.app.ws.ui.model.response.impl.Site;

import java.util.ArrayList;
import java.util.List;

public class SiteListData {

    private List<Site> site = new ArrayList<>();

    public List<Site> getSiteList() {
        return this.site;
    }

    public void addSite(Site site) {
        this.site.add(site);
    }
}
