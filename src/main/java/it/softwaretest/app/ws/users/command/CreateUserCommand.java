package it.softwaretest.app.ws.users.command;

import it.softwaretest.app.ws.command.AbstractCommand;
import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.UsersService;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.request.impl.CreateUserRequest;
import it.softwaretest.app.ws.ui.model.response.impl.User;

import javax.inject.Inject;

public class CreateUserCommand extends AbstractCommand {

    private User user;
    private UserDto userDto;
    private CreateUserRequest createUserRequest;
    private UsersService usersService;

    @Inject
    public CreateUserCommand(User user, UserDto userDto, UsersService usersService) {
        this.user = user;
        this.userDto = userDto;
        this.usersService = usersService;
    }

    @Override
    public void buildCommands() {
        super.addCommand(this.buildCreateUserCommand());
    }

    private Command buildCreateUserCommand() {
        Command command = new CreateUser(this.user, this.userDto, this.createUserRequest, this.usersService);

        return command;
    }

    public void setRequest(CreateUserRequest createUserRequest) {
        this.createUserRequest = createUserRequest;
    }
}
