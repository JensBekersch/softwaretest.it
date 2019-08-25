package it.softwaretest.app.ws.service;

import it.softwaretest.app.ws.shared.dto.impl.SitesDto;

import java.util.List;

public interface SitesServiceInterface {

    SitesDto createSite(SitesDto site);

    SitesDto getSite(long id);

    List<SitesDto> getSitesList(long projet_id);

    void updateSite(SitesDto site);

    void deleteSite(SitesDto site);

}
