package it.softwaretest.app.ws.siteElements.command;

import it.softwaretest.app.ws.command.AbstractCommand;
import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.SiteElementsService;
import it.softwaretest.app.ws.shared.dto.impl.SiteElementsDto;
import it.softwaretest.app.ws.ui.model.request.impl.DeleteSiteElementsRequest;

import javax.inject.Inject;

public class DeleteSiteElementsCommand extends AbstractCommand {

    private DeleteSiteElementsRequest deleteSiteElementsRequest;
    private SiteElementsDto siteElementsDto;
    private SiteElementsService siteElementsService;

    @Inject
    public DeleteSiteElementsCommand(SiteElementsDto siteElementsDto, SiteElementsService siteElementsService) {
        this.siteElementsDto = siteElementsDto;
        this.siteElementsService = siteElementsService;
    }

    @Override
    public void buildCommands() {
        super.addCommand(this.buildDeleteSiteElementsCommand());
    }

    private Command buildDeleteSiteElementsCommand() {
        Command command = new DeleteSiteElements(this.siteElementsDto, this.siteElementsService, this.deleteSiteElementsRequest);

        return command;
    }

    public void setDeleteSiteElementsRequest(DeleteSiteElementsRequest deleteSiteElementsRequest) {
        this.deleteSiteElementsRequest = deleteSiteElementsRequest;
    }

}
