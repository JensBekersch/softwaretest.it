package it.softwaretest.app.ws.project.command;

import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.ui.model.request.impl.CreateProjectRequest;
import it.softwaretest.app.ws.ui.model.response.impl.Project;
import org.springframework.beans.BeanUtils;

public class CreateProject implements Command {

    private Project project;
    private ProjectDto projectDto;
    private CreateProjectRequest createProjectRequest;
    private ProjectService projectService;

    public CreateProject(Project project, ProjectDto projectDto, CreateProjectRequest createProjectRequest, ProjectService projectService) {
        this.project = project;
        this.projectDto = projectDto;
        this.createProjectRequest = createProjectRequest;
        this.projectService = projectService;
    }

    public void execute() {
        BeanUtils.copyProperties(this.createProjectRequest, this.projectDto);
        ProjectDto createdProject = this.projectService.createProject(this.projectDto);
        BeanUtils.copyProperties(createdProject, this.project);
    }
}