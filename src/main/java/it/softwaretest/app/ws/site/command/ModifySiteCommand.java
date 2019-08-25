package it.softwaretest.app.ws.site.command;

import it.softwaretest.app.ws.command.AbstractCommand;
import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.service.impl.SitesService;
import it.softwaretest.app.ws.shared.dto.impl.SitesDto;
import it.softwaretest.app.ws.ui.model.request.impl.UpdateSiteRequest;

import javax.inject.Inject;

public class ModifySiteCommand extends AbstractCommand {

    private ProjectService projectService;
    private SitesService service;
    private SitesDto sitesDto;
    private UpdateSiteRequest request;

    @Inject
    public ModifySiteCommand(ProjectService projectService, SitesService service, SitesDto sitesDto) {
        this.projectService = projectService;
        this.service = service;
        this.sitesDto = sitesDto;
    }

    @Override
    public void buildCommands() {
        super.addCommand(this.modifySiteCommand());
    }

    private Command modifySiteCommand() {
        Command command = new ModifySite(this.projectService, this.service, this.sitesDto, this.request);

        return command;
    }

    public void setUpdateRequest(UpdateSiteRequest request) {
        this.request = request;
    }
}
