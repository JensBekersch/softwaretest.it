package it.softwaretest.app.ws.User.Command;

import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.service.impl.UsersService;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.request.impl.CreateUserRequest;
import it.softwaretest.app.ws.ui.model.request.impl.UpdateUserRequest;
import it.softwaretest.app.ws.ui.model.response.impl.User;
import org.springframework.beans.BeanUtils;

public class ModifyUser implements Command {

    private String id;
    private User user;
    private UserDto userDto;
    private UsersService usersService;
    private UpdateUserRequest userDetails;


    public ModifyUser(String id, User user, UserDto userDto, UsersService usersService, UpdateUserRequest userDetails) {
        this.id = id;
        this.user = user;
        this.userDto = userDto;
        this.usersService = usersService;
        this.userDetails = userDetails;
    }

    @Override
    public void execute() {
        this.userDto = usersService.getUser(this.id);

        if(this.userDetails.getFirstName() != null && !this.userDetails.getFirstName().isEmpty()) {
            this.userDto.setFirstName(userDetails.getFirstName());
        }
        this.userDto.setLastName(userDetails.getLastName());

        usersService.updateUserDetails(this.userDto);

        BeanUtils.copyProperties(this.userDto, this.user);
    }
}
