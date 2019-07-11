package it.softwaretest.app.ws.users.command;

import it.softwaretest.app.ws.command.AbstractCommand;
import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.UsersService;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.response.impl.DeleteUserProfileResponse;
import it.softwaretest.app.ws.ui.model.response.impl.User;

import javax.inject.Inject;

public class DeleteUserCommand extends AbstractCommand {

    private String id;
    private User user;
    private UserDto userDto;
    private UsersService usersService;
    private DeleteUserProfileResponse deleteUserProfileResponse;

    @Inject
    public DeleteUserCommand(User user, UserDto userDto, UsersService usersService, DeleteUserProfileResponse deleteUserProfileResponse) {
        this.user = user;
        this.userDto = userDto;
        this.usersService = usersService;
        this.deleteUserProfileResponse = deleteUserProfileResponse;
    }

    @Override
    public void buildCommands() {
        this.addCommand(this.buildDeleteUserCommand());
    }

    private Command buildDeleteUserCommand() {
        Command command = new DeleteUser(this.id, this.user, this.userDto, this.usersService, this.deleteUserProfileResponse);

        return command;
    }

    public void setId(String id) {
        this.id = id;
    }

}
