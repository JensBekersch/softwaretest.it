package it.softwaretest.app.ws.siteElements.builder;

public class SiteElementsDirector {

    private GetSiteElementsBuilder builder;

    public void setBuilder(GetSiteElementsBuilder builder) {
        this.builder = builder;
    }

    public void buildSiteElemets(long id) {
        this.builder.getSiteDataAndCopyToViewModel(id);
    }

}
