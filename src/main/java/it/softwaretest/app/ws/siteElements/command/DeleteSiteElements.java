package it.softwaretest.app.ws.siteElements.command;

import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.SiteElementsService;
import it.softwaretest.app.ws.shared.dto.impl.SiteElementsDto;
import it.softwaretest.app.ws.ui.model.request.impl.DeleteSiteElementsRequest;

import java.io.IOException;

public class DeleteSiteElements implements Command {

    private SiteElementsDto siteElementsDto;
    private SiteElementsService siteElementsService;
    private DeleteSiteElementsRequest deleteSiteElementsRequest;

    public DeleteSiteElements(SiteElementsDto siteElementsDto, SiteElementsService siteElementsService, DeleteSiteElementsRequest deleteSiteElementsRequest) {
        this.siteElementsDto = siteElementsDto;
        this.siteElementsService = siteElementsService;

        this.deleteSiteElementsRequest = deleteSiteElementsRequest;
    }

    @Override
    public void execute() throws IOException {
        this.siteElementsDto = this.siteElementsService.getElements(this.deleteSiteElementsRequest.getSiteId());

        this.siteElementsService.deleteSiteElements(this.siteElementsDto);
    }

}
