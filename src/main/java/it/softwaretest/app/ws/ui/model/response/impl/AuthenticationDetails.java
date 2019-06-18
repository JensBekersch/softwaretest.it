package it.softwaretest.app.ws.ui.model.response.impl;

import it.softwaretest.app.ws.ui.model.response.AuthenticationDetailsInterface;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthenticationDetails implements AuthenticationDetailsInterface {

    private String id;
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
