package it.softwaretest.app.ws.io.entity.impl;

import it.softwaretest.app.ws.io.entity.UserEntityInterface;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;

@Entity(name = "Users")
public class UserEntity implements UserEntityInterface, Serializable {

    private static final long serialVersionUID = -1666749705772479338L;

    @Id
    @GeneratedValue
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String salt;
    private String encryptedPassword;
    private String token;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEncryptedPassword() {

        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {

        this.encryptedPassword = encryptedPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
