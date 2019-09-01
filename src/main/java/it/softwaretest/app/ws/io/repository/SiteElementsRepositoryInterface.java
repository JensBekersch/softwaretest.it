package it.softwaretest.app.ws.io.repository;

import it.softwaretest.app.ws.shared.dto.impl.SiteElementsDto;

public interface SiteElementsRepositoryInterface {
    void openConnection();
    SiteElementsDto getSiteElementsById(long id);
    SiteElementsDto savesiteElements(SiteElementsDto siteElementsDto);
    void updateSiteElements(SiteElementsDto siteElementsDto);
    void deleteSiteElements(SiteElementsDto siteElementsDto);
    void closeConnection();
}
