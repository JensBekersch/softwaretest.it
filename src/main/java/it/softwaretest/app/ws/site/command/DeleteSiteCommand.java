package it.softwaretest.app.ws.site.command;

import it.softwaretest.app.ws.command.AbstractCommand;
import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.service.impl.SitesService;
import it.softwaretest.app.ws.ui.model.request.impl.DeleteSiteRequest;

import javax.inject.Inject;

public class DeleteSiteCommand extends AbstractCommand {

    private ProjectService projectService;
    private SitesService sitesService;
    private DeleteSiteRequest deleteSiteRequest;

    @Inject
    public DeleteSiteCommand(ProjectService projectService, SitesService sitesService) {
        this.projectService = projectService;
        this.sitesService = sitesService;
    }

    @Override
    public void buildCommands() {
        super.addCommand(this.deleteSiteCommand());
    }

    private Command deleteSiteCommand() {
        Command command = new DeleteSite(this.projectService, this.sitesService, this.deleteSiteRequest);

        return command;
    }

    public void setDeleteSiteRequest(DeleteSiteRequest deleteSiteRequest) {
        this.deleteSiteRequest = deleteSiteRequest;
    }

}
