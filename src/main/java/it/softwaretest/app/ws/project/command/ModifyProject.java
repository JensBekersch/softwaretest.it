package it.softwaretest.app.ws.project.command;

import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.exceptions.NoRecordFoundException;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.ui.model.request.impl.UpdateProjectRequest;
import it.softwaretest.app.ws.ui.model.response.impl.Project;

public class ModifyProject implements Command {

    private ProjectService projectService;
    private Project project;
    private ProjectDto projectDto;
    private UpdateProjectRequest projectRequest;

    public ModifyProject(ProjectService projectService, Project project, ProjectDto projectDto, UpdateProjectRequest projectRequest) {
        this.projectService = projectService;
        this.project = project;
        this.projectDto = projectDto;
        this.projectRequest = projectRequest;
    }

    @Override
    public void execute() {
        this.projectDto = this.projectService.getProject(this.projectRequest.getProjectId());

        if (this.projectDto.getUserId().equals(this.projectRequest.getUserId())) {
            this.projectDto.setName(this.projectRequest.getNewName());
            this.projectService.updateProject(this.projectDto);
        } else {
            throw new NoRecordFoundException("Project Id was not found!");
        }
    }
}
