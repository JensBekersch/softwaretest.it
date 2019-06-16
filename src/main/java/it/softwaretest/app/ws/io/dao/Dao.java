package it.softwaretest.app.ws.io.dao;

import it.softwaretest.app.ws.shared.dto.UserDto;

public interface Dao {
    void openConnection();
    UserDto getUserByUserName(String userName);
    UserDto saveUser(UserDto user);
    UserDto getUser(String id);
    void updateUser(UserDto userProfile);
    void deleteUser(UserDto userProfile);
    void closeConnection();
}
