package it.softwaretest.app.ws.service;

import it.softwaretest.app.ws.exceptions.AuthenticationException;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;

public interface AuthenticationServiceInterface {
    UserDto authenticate(String userName, String password) throws AuthenticationException;
    String issueAccessToken(UserDto userProfile) throws AuthenticationException;
    void resetSecurityCredentials(String password, UserDto userProfile);
}
