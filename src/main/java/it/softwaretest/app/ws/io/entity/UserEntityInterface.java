package it.softwaretest.app.ws.io.entity;

public interface UserEntityInterface {

    long getId();

    void setId(long id);

    String getUserId();

    void setUserId(String userId);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getSalt();

    void setSalt(String salt);

    String getEncryptedPassword();

    void setEncryptedPassword(String encryptedPassword);

    String getToken();

    void setToken(String token);
}
