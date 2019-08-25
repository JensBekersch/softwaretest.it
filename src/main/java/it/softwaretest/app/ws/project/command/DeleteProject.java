package it.softwaretest.app.ws.project.command;

import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.exceptions.NoRecordFoundException;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.ui.model.request.impl.DeleteProjectRequest;
import it.softwaretest.app.ws.ui.model.response.impl.Project;

public class DeleteProject implements Command {

    private ProjectService projectService;
    private Project project;
    private ProjectDto projectDto;
    private DeleteProjectRequest deleteRequest;

    public DeleteProject(ProjectService projectService, Project project, ProjectDto projectDto, DeleteProjectRequest deleteRequest) {
        this.projectService = projectService;
        this.project = project;
        this.projectDto = projectDto;
        this.deleteRequest = deleteRequest;
    }

    @Override
    public void execute() {
        this.projectDto = this.projectService.getProject(this.deleteRequest.getProjectId());

        if (this.projectDto.getUserId().equals(this.deleteRequest.getUserId())) {
            this.projectService.deleteProject(this.projectDto);
        } else {
            throw new NoRecordFoundException("Project Id was not found!");
        }
    }

}
