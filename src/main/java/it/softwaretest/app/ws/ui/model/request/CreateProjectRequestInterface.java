package it.softwaretest.app.ws.ui.model.request;

import it.softwaretest.app.ws.ui.model.response.impl.User;

public interface CreateProjectRequestInterface {

    String getUserId();

    void setUserId(String userId);

    String getName();

    void setName(String name);

}
