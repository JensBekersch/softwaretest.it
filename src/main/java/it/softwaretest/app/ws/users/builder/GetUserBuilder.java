package it.softwaretest.app.ws.users.builder;

import it.softwaretest.app.ws.service.impl.UsersService;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.response.impl.User;
import org.springframework.beans.BeanUtils;

import javax.inject.Inject;

public class GetUserBuilder implements GetUserBuilderInterface {

    private User user;
    private UserDto userDto;
    private UsersService usersService;

    protected UserData userData;

    @Inject
    public GetUserBuilder(User user, UserDto userDto, UsersService usersService) {
        this.user = user;
        this.userDto = userDto;
        this.usersService = usersService;
        this.reset();
    }

    private void reset() {
        this.userData = new UserData();
    }

    @Override
    public void getUserDataAndCopyDtoToUserModel(String id) {
        this.userDto = this.usersService.getUser(id);
        BeanUtils.copyProperties(this.userDto, this.user);

        this.userData.setUser(this.user);
    }

    public UserData getUserData() {
        UserData buildedUserData = this.userData;
        this.reset();
        return buildedUserData;
    }

}
