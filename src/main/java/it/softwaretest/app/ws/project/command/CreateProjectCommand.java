package it.softwaretest.app.ws.project.command;

import it.softwaretest.app.ws.command.AbstractCommand;
import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.ui.model.request.impl.CreateProjectRequest;
import it.softwaretest.app.ws.ui.model.response.impl.Project;

import javax.inject.Inject;

public class CreateProjectCommand extends AbstractCommand {

    private Project project;
    private ProjectDto projectDto;
    private ProjectService projectService;
    private CreateProjectRequest createProjectRequest;

    @Inject
    public CreateProjectCommand(Project project, ProjectDto projectDto, ProjectService projectService) {
        this.project = project;
        this.projectDto = projectDto;
        this.projectService = projectService;
    }

    @Override
    public void buildCommands() {
        super.addCommand(this.createProjectCommand());
    }

    private Command createProjectCommand() {
        Command command = new CreateProject(this.project, this.projectDto, this.createProjectRequest, this.projectService);

        return command;
    }

    public void setProject(CreateProjectRequest createProjectRequest) {
        this.createProjectRequest = createProjectRequest;
    }

}
