package it.softwaretest.app.ws.site.builder.siteListBuilder;

public class SiteListDirector {

    private GetSiteListBuilder siteListBuilder;

    public void setSiteListBuilder(GetSiteListBuilder siteListBuilder) {
        this.siteListBuilder = siteListBuilder;
    }

    public void buildSiteList(long id, String userId) {
        this.siteListBuilder.getSiteListByProjectId(id, userId);
    }

}
