package it.softwaretest.app.ws.shared.dto;

import it.softwaretest.app.ws.ui.model.response.UserInterface;
import it.softwaretest.app.ws.ui.model.response.impl.User;

public interface UserDtoInterface {

    long getId();

    void setId(long id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getSalt();

    void setSalt(String salt);

    String getEncryptedPassword();

    void setEncryptedPassword(String encryptedPassword);

    String getUserId();

    void setUserId(String userId);

    String getToken();

    void setToken(String token);

}
