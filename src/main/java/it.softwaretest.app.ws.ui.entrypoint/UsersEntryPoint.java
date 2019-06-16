package it.softwaretest.app.ws.ui.entrypoint;

import it.softwaretest.app.ws.annotations.Secured;
import it.softwaretest.app.ws.service.UsersService;
import it.softwaretest.app.ws.service.impl.UsersServiceImpl;
import it.softwaretest.app.ws.shared.dto.UserDto;
import it.softwaretest.app.ws.ui.model.request.CreateUserRequestModel;
import it.softwaretest.app.ws.ui.model.request.UpdateUserRequestModel;
import it.softwaretest.app.ws.ui.model.response.DeleteUserProfileResponseModel;
import it.softwaretest.app.ws.ui.model.response.RequestOperation;
import it.softwaretest.app.ws.ui.model.response.ResponseStatus;
import it.softwaretest.app.ws.ui.model.response.UserModel;
import org.springframework.beans.BeanUtils;
import sun.plugin.util.UserProfile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UsersEntryPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel createUser(CreateUserRequestModel requestModel) {
        UserModel userModel = new UserModel();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(requestModel, userDto);

        UsersService usersService = new UsersServiceImpl();
        UserDto createdUserProfile = usersService.createUser(userDto);

        BeanUtils.copyProperties(createdUserProfile, userModel);

        return userModel;
    }

    @Secured
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel getUserProfile(@PathParam("id") String id) {
        UserModel returnValue = null;

        UsersService usersService = new UsersServiceImpl();
        UserDto userProfileDto = usersService.getUser(id);

        returnValue = new UserModel();
        BeanUtils.copyProperties(userProfileDto, returnValue);

        return returnValue;
    }

    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel updateUserDetails(@PathParam("id") String id, UpdateUserRequestModel userDetails) {

        UsersService usersService = new UsersServiceImpl();
        UserDto storedUserDetails = usersService.getUser(id);

        if(userDetails.getFirstName() != null && !userDetails.getFirstName().isEmpty()) {
            storedUserDetails.setFirstName(userDetails.getFirstName());
        }
        storedUserDetails.setLastName(userDetails.getLastName());

        usersService.updateUserDetails(storedUserDetails);

        UserModel returnValue = new UserModel();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Secured
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DeleteUserProfileResponseModel deleteUserProfile(@PathParam("id") String id) {

        UsersService usersService = new UsersServiceImpl();
        DeleteUserProfileResponseModel returnValue = new DeleteUserProfileResponseModel();
        returnValue.setRequestOperation(RequestOperation.DELETE);

        UserDto storedUserDetails = usersService.getUser(id);

        usersService.deleteUser(storedUserDetails);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }

}
