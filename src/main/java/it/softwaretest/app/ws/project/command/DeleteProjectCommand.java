package it.softwaretest.app.ws.project.command;

import it.softwaretest.app.ws.command.AbstractCommand;
import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.ui.model.request.impl.DeleteProjectRequest;
import it.softwaretest.app.ws.ui.model.response.impl.Project;

import javax.inject.Inject;

public class DeleteProjectCommand extends AbstractCommand {

    private DeleteProjectRequest deleteRequest;
    private Project project;
    private ProjectDto projectDto;
    private ProjectService projectService;

    @Inject
    public DeleteProjectCommand(Project project, ProjectDto projectDto, ProjectService projectService) {
        this.project = project;
        this.projectDto = projectDto;
        this.projectService = projectService;
    }

    @Override
    public void buildCommands() {
        super.addCommand(this.buildModifyProjectCommand());
    }

    private Command buildModifyProjectCommand() {
        Command command = new DeleteProject(this.projectService, this.project, this.projectDto, this.deleteRequest);

        return command;
    }

    public void setDeleteProjectRequest(DeleteProjectRequest deleteRequest) {
        this.deleteRequest = deleteRequest;
    }

}
