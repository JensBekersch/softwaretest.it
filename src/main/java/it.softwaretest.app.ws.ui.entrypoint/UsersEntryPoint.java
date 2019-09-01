package it.softwaretest.app.ws.ui.entrypoint;

import it.softwaretest.app.ws.users.builder.GetUserBuilder;
import it.softwaretest.app.ws.users.builder.UserDirector;
import it.softwaretest.app.ws.users.command.DeleteUserCommand;
import it.softwaretest.app.ws.users.command.ModifyUserCommand;
import it.softwaretest.app.ws.annotations.Secured;
import it.softwaretest.app.ws.users.command.CreateUserCommand;
import it.softwaretest.app.ws.ui.model.request.impl.CreateUserRequest;
import it.softwaretest.app.ws.ui.model.request.impl.UpdateUserRequest;
import it.softwaretest.app.ws.ui.model.response.impl.DeleteUserProfileResponse;
import it.softwaretest.app.ws.ui.model.response.impl.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UsersEntryPoint {

    @Autowired
    private User user;

    @Autowired
    private CreateUserCommand createUserCommand;

    @Autowired
    private ModifyUserCommand modifyUserCommand;

    @Autowired
    private DeleteUserCommand deleteUserCommand;

    @Autowired
    private DeleteUserProfileResponse deleteUserProfileResponse;

    @Autowired
    private UserDirector userDirector;

    @Autowired
    private GetUserBuilder userBuilder;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(CreateUserRequest createUserRequest) throws Exception {
        createUserCommand.setRequest(createUserRequest);
        createUserCommand.executeCommands();

        return Response.ok(user).status(201).build();
    }

    @Secured
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfile(@PathParam("id") String id) {
        userDirector.setBuilder(this.userBuilder);
        userDirector.buildUser(id);

        return Response.ok(this.userBuilder.getUserData().getUser()).status(200).build();
    }

    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserDetails(@PathParam("id") String id, UpdateUserRequest userDetails) throws Exception {
        modifyUserCommand.setId(id);
        modifyUserCommand.setUpdateUserRequest(userDetails);
        modifyUserCommand.executeCommands();

        return Response.status(204).build();
    }

    @Secured
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserProfile(@PathParam("id") String id) throws Exception {
        deleteUserCommand.setId(id);
        deleteUserCommand.executeCommands();

        return Response.status(204).build();
    }

}
