package it.softwaretest.app.ws.project.command;

import it.softwaretest.app.ws.command.AbstractCommand;
import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.service.impl.UsersService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.request.impl.UpdateProjectRequest;
import it.softwaretest.app.ws.ui.model.request.impl.UpdateUserRequest;
import it.softwaretest.app.ws.ui.model.response.impl.Project;
import it.softwaretest.app.ws.ui.model.response.impl.User;

import javax.inject.Inject;

public class ModifyProjectCommand extends AbstractCommand {

    private UpdateProjectRequest projectRequest;
    private Project project;
    private ProjectDto projectDto;
    private ProjectService projectService;

    @Inject
    public ModifyProjectCommand(Project project, ProjectDto projectDto, ProjectService projectService) {
        this.project = project;
        this.projectDto = projectDto;
        this.projectService = projectService;
    }

    @Override
    public void buildCommands() {
        super.addCommand(this.buildModifyProjectCommand());
    }

    private Command buildModifyProjectCommand() {
        Command command = new ModifyProject(this.projectService, this.project, this.projectDto, this.projectRequest);

        return command;
    }

    public void setUpdateProjectRequest(UpdateProjectRequest projectRequest) {
        this.projectRequest = projectRequest;
    }

}
