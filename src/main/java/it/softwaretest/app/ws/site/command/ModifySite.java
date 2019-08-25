package it.softwaretest.app.ws.site.command;

import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.exceptions.NoRecordFoundException;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.service.impl.SitesService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.shared.dto.impl.SitesDto;
import it.softwaretest.app.ws.ui.model.request.impl.UpdateSiteRequest;
import org.springframework.beans.BeanUtils;

public class ModifySite implements Command {

    private ProjectService projectService;
    private SitesService service;
    private SitesDto sitesDto;
    private UpdateSiteRequest request;

    public ModifySite(ProjectService projectService, SitesService service, SitesDto sitesDto, UpdateSiteRequest request) {
        this.projectService = projectService;
        this.service = service;
        this.sitesDto = sitesDto;
        this.request = request;
    }

    @Override
    public void execute() {
        SitesDto site = this.service.getSite(this.request.getId());
        ProjectDto project = this.projectService.getProject(site.getProject_id());
        if (this.request.getUserId().equals(project.getUserId())) {
            site.setName(this.request.getNewName());
            this.service.updateSite(site);
        } else {
            throw new NoRecordFoundException("Site Id was not found!");
        }
    }
}
