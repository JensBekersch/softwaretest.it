package it.softwaretest.app.ws.site.builder.siteBuilder;

public class SiteDirector {

    private GetSiteBuilder siteBuilder;

    public void setSiteBuilder(GetSiteBuilder siteBuilder) {
        this.siteBuilder = siteBuilder;
    }

    public void buildSite(long id, String userId) {
        this.siteBuilder.getSiteById(id, userId);
    }

}
