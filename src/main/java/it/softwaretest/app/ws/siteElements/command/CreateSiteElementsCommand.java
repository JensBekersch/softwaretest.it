package it.softwaretest.app.ws.siteElements.command;

import it.softwaretest.app.ws.command.AbstractCommand;
import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.service.impl.SiteElementsService;
import it.softwaretest.app.ws.service.impl.SitesService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.shared.dto.impl.SiteElementsDto;
import it.softwaretest.app.ws.shared.dto.impl.SitesDto;
import it.softwaretest.app.ws.ui.model.request.impl.CreateSiteElementsRequest;

import javax.inject.Inject;

public class CreateSiteElementsCommand extends AbstractCommand {

    private CreateSiteElementsRequest createSiteElementsRequest;
    private SiteElementsDto siteElementsDto;
    private SiteElementsService service;
    private SitesService sitesService;
    private ProjectService projectService;
    private ProjectDto projectDto;
    private SitesDto sitesDto;
    private SiteElementsService siteElementsService;

    @Inject
    public CreateSiteElementsCommand(
        SiteElementsDto siteElementsDto,
        SiteElementsService service,
        SitesService sitesService,
        ProjectService projectService,
        ProjectDto projectDto,
        SitesDto sitesDto,
        SiteElementsService siteElementsService
    ) {
        this.siteElementsDto = siteElementsDto;
        this.service = service;
        this.sitesService = sitesService;
        this.projectService = projectService;
        this.projectDto = projectDto;
        this.sitesDto = sitesDto;
        this.siteElementsService = siteElementsService;
    }

    @Override
    public void buildCommands() {
        super.addCommand(this.buildCreateSiteElementsCommand());
    }

    public Command buildCreateSiteElementsCommand() {
        Command command = new CreateSiteElements(this.siteElementsDto, this.createSiteElementsRequest, this.service, this.sitesService, this.projectService, this.projectDto, this.sitesDto, this.siteElementsService);

        return command;
    }

    public void setCreateSiteElementsRequest(CreateSiteElementsRequest createSiteElementsRequest) {
        this.createSiteElementsRequest = createSiteElementsRequest;
    }

}
