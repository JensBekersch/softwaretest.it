package it.softwaretest.app.ws.service;

import it.softwaretest.app.ws.shared.dto.UserDto;

public interface UsersService {
    UserDto createUser(UserDto user);
    UserDto getUser(String id);
    UserDto getUserByUserName(String userName);
    void updateUserDetails(UserDto userDetails);
    void deleteUser(UserDto userDto);
}
