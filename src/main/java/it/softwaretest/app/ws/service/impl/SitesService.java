package it.softwaretest.app.ws.service.impl;

import it.softwaretest.app.ws.exceptions.CouldNotDeleteRecordException;
import it.softwaretest.app.ws.exceptions.CouldNotUpdateRecordException;
import it.softwaretest.app.ws.exceptions.NoRecordFoundException;
import it.softwaretest.app.ws.io.repository.impl.SitesRepository;
import it.softwaretest.app.ws.service.SitesServiceInterface;
import it.softwaretest.app.ws.shared.dto.impl.SitesDto;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import java.util.List;

public class SitesService implements SitesServiceInterface {

    private SitesRepository repository;

    public SitesService(SitesRepository repository) {

        this.repository = repository;
    }

    @Override
    public SitesDto createSite(SitesDto site) {
        SitesDto returnValue;

        try {
            this.repository.openConnection();
            returnValue = this.repository.saveSite(site);
        } finally {
            this.repository.closeConnection();
        }

        return returnValue;
    }

    @Override
    public SitesDto getSite(long id) {
        SitesDto sitesDto;

        try {
            this.repository.openConnection();
            sitesDto = this.repository.getSiteById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessageDefinitions.NO_RECORD_FOUND.getErrorMessage());
        } finally {
            this.repository.closeConnection();
        }

        return sitesDto;
    }

    @Override
    public List<SitesDto> getSitesList(long project_id) {
        List<SitesDto> sitesList;

        try {
            this.repository.openConnection();
            sitesList = this.repository.getSitesByProjectId(project_id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessageDefinitions.NO_RECORD_FOUND.getErrorMessage());
        } finally {
            this.repository.closeConnection();
        }

        return sitesList;
    }

    @Override
    public void updateSite(SitesDto site) {
        try {
            this.repository.openConnection();
            this.repository.updateSite(site);
        } catch (Exception ex) {
            throw new CouldNotUpdateRecordException(ex.getMessage());
        } finally {
            this.repository.closeConnection();
        }
    }

    @Override
    public void deleteSite(SitesDto site) {
        try {
            this.repository.openConnection();
            this.repository.deleteSite(site);
        } catch (Exception ex) {
            throw new CouldNotDeleteRecordException(ex.getMessage());
        } finally {
            this.repository.closeConnection();
        }

        try {
            site = getSite(site.getId());
        } catch (NoRecordFoundException ex) {
            site = null;
        }

        if (site != null)
            throw new CouldNotDeleteRecordException(ErrorMessageDefinitions.COULD_NOT_DELETE_RECORD.getErrorMessage());
    }
}
