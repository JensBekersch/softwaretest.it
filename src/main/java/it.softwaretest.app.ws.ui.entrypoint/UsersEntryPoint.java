package it.softwaretest.app.ws.ui.entrypoint;

import it.softwaretest.app.ws.annotations.Secured;
import it.softwaretest.app.ws.service.UsersServiceInterface;
import it.softwaretest.app.ws.service.impl.UsersService;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.request.impl.CreateUserRequest;
import it.softwaretest.app.ws.ui.model.request.impl.UpdateUserRequest;
import it.softwaretest.app.ws.ui.model.response.impl.DeleteUserProfileResponse;
import it.softwaretest.app.ws.ui.model.response.impl.RequestOperation;
import it.softwaretest.app.ws.ui.model.response.impl.ResponseStatus;
import it.softwaretest.app.ws.ui.model.response.impl.User;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UsersEntryPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(CreateUserRequest requestModel) {
        User userModel = new User();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(requestModel, userDto);

        UsersServiceInterface usersService = new UsersService();
        UserDto createdUserProfile = usersService.createUser(userDto);

        BeanUtils.copyProperties(createdUserProfile, userModel);

        return userModel;
    }

    @Secured
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserProfile(@PathParam("id") String id) {
        User returnValue = null;

        UsersService usersService = new UsersService();
        UserDto userProfileDto = usersService.getUser(id);

        returnValue = new User();
        BeanUtils.copyProperties(userProfileDto, returnValue);

        return returnValue;
    }

    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUserDetails(@PathParam("id") String id, UpdateUserRequest userDetails) {

        UsersServiceInterface usersService = new UsersService();
        UserDto storedUserDetails = usersService.getUser(id);

        if(userDetails.getFirstName() != null && !userDetails.getFirstName().isEmpty()) {
            storedUserDetails.setFirstName(userDetails.getFirstName());
        }
        storedUserDetails.setLastName(userDetails.getLastName());

        usersService.updateUserDetails(storedUserDetails);

        User returnValue = new User();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Secured
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DeleteUserProfileResponse deleteUserProfile(@PathParam("id") String id) {

        UsersServiceInterface usersService = new UsersService();
        DeleteUserProfileResponse returnValue = new DeleteUserProfileResponse();
        returnValue.setRequestOperation(RequestOperation.DELETE);

        UserDto storedUserDetails = usersService.getUser(id);

        usersService.deleteUser(storedUserDetails);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }

}
