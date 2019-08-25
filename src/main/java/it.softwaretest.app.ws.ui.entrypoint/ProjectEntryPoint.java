package it.softwaretest.app.ws.ui.entrypoint;

import it.softwaretest.app.ws.annotations.Secured;
import it.softwaretest.app.ws.project.builder.GetProjectBuilder;
import it.softwaretest.app.ws.project.builder.ProjectDirector;
import it.softwaretest.app.ws.project.command.CreateProjectCommand;
import it.softwaretest.app.ws.project.command.DeleteProjectCommand;
import it.softwaretest.app.ws.project.command.ModifyProjectCommand;
import it.softwaretest.app.ws.ui.model.request.impl.CreateProjectRequest;
import it.softwaretest.app.ws.ui.model.request.impl.DeleteProjectRequest;
import it.softwaretest.app.ws.ui.model.request.impl.UpdateProjectRequest;
import it.softwaretest.app.ws.ui.model.response.impl.Project;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projects")
public class ProjectEntryPoint {

    @Autowired
    private Project project;

    @Autowired
    private CreateProjectCommand createProjectCommand;

    @Autowired
    private ProjectDirector projectDirector;

    @Autowired
    private GetProjectBuilder projectBuilder;

    @Autowired
    private ModifyProjectCommand modifyProjectCommand;

    @Autowired
    private DeleteProjectCommand deleteProjectCommand;

    @Secured
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(@PathParam("id") String id, CreateProjectRequest createProjectRequest) {
        createProjectRequest.setUserId(id);
        createProjectCommand.setProject(createProjectRequest);
        createProjectCommand.executeCommands();

        return Response.status(201).build();
    }

    @Secured
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjects(@PathParam("id") String id) {
        projectDirector.setBuilder(this.projectBuilder);
        projectDirector.buildProjectList(id);

        return Response.ok(this.projectBuilder.getProjectList().getProjects()).status(200).build();
    }

    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProject(@PathParam("id") String id, UpdateProjectRequest updateProjectRequest) {
        updateProjectRequest.setUserId(id);
        modifyProjectCommand.setUpdateProjectRequest(updateProjectRequest);
        modifyProjectCommand.executeCommands();

        return Response.status(204).build();
    }

    @Secured
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProject(@PathParam("id") String id, DeleteProjectRequest deleteProjectRequest) {
        deleteProjectRequest.setUserId(id);
        deleteProjectCommand.setDeleteProjectRequest(deleteProjectRequest);
        deleteProjectCommand.executeCommands();

        return Response.status(204).build();
    }

}
