package it.softwaretest.app.ws.site.command;

import it.softwaretest.app.ws.command.AbstractCommand;
import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.service.impl.SitesService;
import it.softwaretest.app.ws.shared.dto.impl.SitesDto;
import it.softwaretest.app.ws.ui.model.request.impl.CreateSiteRequest;

import javax.inject.Inject;

public class CreateSiteCommand extends AbstractCommand {

    private SitesDto sitesDto;
    private CreateSiteRequest request;
    private SitesService service;
    private ProjectService projectService;

    @Inject
    public CreateSiteCommand(SitesDto sitesDto, SitesService service, ProjectService projectService) {
        this.sitesDto = sitesDto;
        this.service = service;
        this.projectService = projectService;
    }

    @Override
    public void buildCommands() {
        super.addCommand(this.createSiteCommand());
    }

    public Command createSiteCommand() {
        Command command = new CreateSite(this.sitesDto, this.request, this.service, this.projectService);

        return command;
    }

    public void setSiteRequest(CreateSiteRequest request) {
        this.request = request;
    }

}
