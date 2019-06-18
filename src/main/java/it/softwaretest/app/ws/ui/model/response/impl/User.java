package it.softwaretest.app.ws.ui.model.response.impl;

import it.softwaretest.app.ws.ui.model.response.UserInterface;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User implements UserInterface {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String href;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
