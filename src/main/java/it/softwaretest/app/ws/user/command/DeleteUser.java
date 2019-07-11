package it.softwaretest.app.ws.user.command;

import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.UsersService;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.response.impl.DeleteUserProfileResponse;
import it.softwaretest.app.ws.ui.model.response.impl.RequestOperation;
import it.softwaretest.app.ws.ui.model.response.impl.ResponseStatus;
import it.softwaretest.app.ws.ui.model.response.impl.User;

public class DeleteUser implements Command {

    private String id;
    private User user;
    private UserDto userDto;
    private UsersService usersService;
    private DeleteUserProfileResponse deleteUserProfileResponse;

    public DeleteUser(String id, User user, UserDto userDto, UsersService usersService, DeleteUserProfileResponse deleteUserProfileResponse) {
        this.id = id;
        this.user = user;
        this.userDto = userDto;
        this.usersService = usersService;
        this.deleteUserProfileResponse = deleteUserProfileResponse;
    }


    @Override
    public void execute() {
        deleteUserProfileResponse.setRequestOperation(RequestOperation.DELETE);
        this.userDto  = usersService.getUser(this.id);
        usersService.deleteUser(this.userDto);

        deleteUserProfileResponse.setResponseStatus(ResponseStatus.SUCCESS);
    }
}
