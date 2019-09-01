package it.softwaretest.app.ws.service;

import it.softwaretest.app.ws.shared.dto.impl.SiteElementsDto;

public interface SiteElementsServiceInterface {
    SiteElementsDto createSiteElements(SiteElementsDto siteElements);
    SiteElementsDto getElements(long id);
    void deleteSiteElements(SiteElementsDto siteElementsDto);
}
