package it.softwaretest.app.ws.ui.model.request.impl;

import it.softwaretest.app.ws.ui.model.request.LoginCredentialsInterface;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginCredentials implements LoginCredentialsInterface {

    private String userName;
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
