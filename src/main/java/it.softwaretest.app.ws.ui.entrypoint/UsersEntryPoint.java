package it.softwaretest.app.ws.ui.entrypoint;

import it.softwaretest.app.ws.User.Builder.GetUserBuilder;
import it.softwaretest.app.ws.User.Builder.UserDirector;
import it.softwaretest.app.ws.User.Command.DeleteUserCommand;
import it.softwaretest.app.ws.User.Command.ModifyUserCommand;
import it.softwaretest.app.ws.annotations.Secured;
import it.softwaretest.app.ws.User.Command.CreateUserCommand;
import it.softwaretest.app.ws.ui.model.request.impl.CreateUserRequest;
import it.softwaretest.app.ws.ui.model.request.impl.UpdateUserRequest;
import it.softwaretest.app.ws.ui.model.response.impl.DeleteUserProfileResponse;
import it.softwaretest.app.ws.ui.model.response.impl.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    public User createUser(CreateUserRequest createUserRequest) {
        createUserCommand.setRequest(createUserRequest);
        createUserCommand.executeCommands();

        return user;
    }

    @Secured
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserProfile(@PathParam("id") String id) {
        userDirector.setBuilder(this.userBuilder);
        userDirector.buildUser(id);

        return this.userBuilder.getUserData().getUser();
    }

    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUserDetails(@PathParam("id") String id, UpdateUserRequest userDetails) {
        modifyUserCommand.setId(id);
        modifyUserCommand.setUpdateUserRequest(userDetails);
        modifyUserCommand.executeCommands();

        return user;
    }

    @Secured
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DeleteUserProfileResponse deleteUserProfile(@PathParam("id") String id) {
        deleteUserCommand.setId(id);
        deleteUserCommand.executeCommands();

        return deleteUserProfileResponse;
    }

}
