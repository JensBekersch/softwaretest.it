package it.softwaretest.app.ws.User.Command;

import it.softwaretest.app.ws.command.AbstractCommand;
import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.UsersService;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.request.impl.UpdateUserRequest;
import it.softwaretest.app.ws.ui.model.response.impl.User;

import javax.inject.Inject;

public class ModifyUserCommand extends AbstractCommand {

    private String id;
    private User user;
    private UserDto userDto;
    private UsersService usersService;
    private UpdateUserRequest userDetails;

    @Inject
    public ModifyUserCommand(User user, UserDto userDto, UsersService usersService) {
        this.user = user;
        this.userDto = userDto;
        this.usersService = usersService;
    }

    @Override
    public void buildCommands() {
        super.addCommand(this.buildModifyUserCommand());
    }

    private Command buildModifyUserCommand() {
        Command command = new ModifyUser(this.id, this.user, this.userDto, this.usersService, this.userDetails);

        return command;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUpdateUserRequest(UpdateUserRequest userDetails) {
        this.userDetails = userDetails;
    }

}
