package it.softwaretest.app.ws.io.repository;

import it.softwaretest.app.ws.shared.dto.impl.SitesDto;

import java.util.List;

public interface SitesRepositoryInterface {
    void openConnection();
    SitesDto getSiteById(long id);
    List<SitesDto> getSitesByProjectId(long id);
    SitesDto saveSite(SitesDto sitesDto);
    void updateSite(SitesDto sitesDto);
    void deleteSite(SitesDto sitesDto);
    void closeConnection();
}
