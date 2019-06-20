package it.softwaretest.app.ws.io.repository;

import it.softwaretest.app.ws.shared.dto.impl.UserDto;

public interface UserRepositoryInterface {
    void openConnection();
    UserDto getUserByUserName(String userName);
    UserDto saveUser(UserDto user);
    UserDto getUser(String id);
    void updateUser(UserDto userProfile);
    void deleteUser(UserDto userProfile);
    void closeConnection();
}
