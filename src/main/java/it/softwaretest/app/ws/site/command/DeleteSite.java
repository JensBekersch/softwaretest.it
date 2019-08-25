package it.softwaretest.app.ws.site.command;

import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.exceptions.NoRecordFoundException;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.service.impl.SitesService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.shared.dto.impl.SitesDto;
import it.softwaretest.app.ws.ui.model.request.impl.DeleteSiteRequest;

public class DeleteSite implements Command {

    private ProjectService projectService;
    private SitesService sitesService;
    private DeleteSiteRequest deleteSiteRequest;

    public DeleteSite(ProjectService projectService, SitesService sitesService, DeleteSiteRequest deleteSiteRequest) {
        this.projectService = projectService;
        this.sitesService = sitesService;
        this.deleteSiteRequest = deleteSiteRequest;
    }

    @Override
    public void execute() {
        SitesDto site = this.sitesService.getSite(this.deleteSiteRequest.getId());
        ProjectDto project = this.projectService.getProject(site.getProject_id());

        if (this.deleteSiteRequest.getUserId().equals(project.getUserId())) {
            this.sitesService.deleteSite(site);
        } else {
            throw new NoRecordFoundException("Es wurde kein Eintrag mit diesem Namen gefunden!");
        }
    }
}
