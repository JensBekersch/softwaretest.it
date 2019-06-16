package it.softwaretest.app.ws.ui.model.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpdateUserRequestModel {

    private String firstName;
    private String lastName;

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

}
