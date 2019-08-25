package it.softwaretest.app.ws.site.command;

import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.service.impl.SitesService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.shared.dto.impl.SitesDto;
import it.softwaretest.app.ws.ui.model.request.impl.CreateSiteRequest;
import org.springframework.beans.BeanUtils;

public class CreateSite implements Command {

    private SitesDto sitesDto;
    private CreateSiteRequest request;
    private SitesService service;
    private ProjectService projectService;

    public CreateSite(SitesDto sitesDto, CreateSiteRequest request, SitesService service, ProjectService projectService) {
        this.sitesDto = sitesDto;
        this.request = request;
        this.service = service;
        this.projectService = projectService;
    }

    public void execute() {
        ProjectDto project = this.projectService.getProject(this.request.getProject_id());

        if(project.getUserId().equals(this.request.getUserId())) {
            BeanUtils.copyProperties(this.request, this.sitesDto);
            this.service.createSite(this.sitesDto);
        }
    }

}
