package it.softwaretest.app.ws.user.command;

import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.UsersService;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.request.impl.CreateUserRequest;
import it.softwaretest.app.ws.ui.model.response.impl.User;
import org.springframework.beans.BeanUtils;

public class CreateUser implements Command {

    private User user;
    private UserDto userDto;
    private CreateUserRequest createUserRequest;
    private UsersService usersService;

    public CreateUser(User user, UserDto userDto, CreateUserRequest createUserRequest, UsersService usersService) {
        this.user = user;
        this.userDto = userDto;
        this.createUserRequest = createUserRequest;
        this.usersService = usersService;
    }

    public void execute() {
        BeanUtils.copyProperties(this.createUserRequest, this.userDto);
        UserDto createdUserProfile = this.usersService.createUser(this.userDto);
        BeanUtils.copyProperties(createdUserProfile, this.user);
    }

}
