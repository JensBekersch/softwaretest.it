package it.softwaretest.app.ws.service.impl;

import it.softwaretest.app.ws.exceptions.CouldNotDeleteRecordException;
import it.softwaretest.app.ws.exceptions.NoRecordFoundException;
import it.softwaretest.app.ws.io.repository.impl.SiteElementsRepository;
import it.softwaretest.app.ws.service.SiteElementsServiceInterface;
import it.softwaretest.app.ws.shared.dto.impl.SiteElementsDto;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

public class SiteElementsService implements SiteElementsServiceInterface {

    private SiteElementsRepository repository;

    public SiteElementsService(SiteElementsRepository repository) {
        this.repository = repository;
    }

    @Override
    public SiteElementsDto createSiteElements(SiteElementsDto siteElements) {
        SiteElementsDto returnValue;

        try {
            this.repository.openConnection();
            returnValue = this.repository.savesiteElements(siteElements);
        } finally {
            this.repository.closeConnection();
        }

        return returnValue;
    }

    @Override
    public SiteElementsDto getElements(long id) {
        SiteElementsDto siteElementsDto;

        try {
            this.repository.openConnection();
            siteElementsDto = this.repository.getSiteElementsById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessageDefinitions.NO_RECORD_FOUND.getErrorMessage());
        } finally {
            this.repository.closeConnection();
        }

        return siteElementsDto;
    }

    @Override
    public void deleteSiteElements(SiteElementsDto siteElementsDto) {
        try {
            this.repository.openConnection();
            this.repository.deleteSiteElements(siteElementsDto);
        } catch (Exception ex) {
            throw new CouldNotDeleteRecordException(ex.getMessage());
        } finally {
            this.repository.closeConnection();
        }
    }
}
