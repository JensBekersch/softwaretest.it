package it.softwaretest.app.ws.io.entity;

import it.softwaretest.app.ws.io.entity.impl.UserEntity;

public interface ProjectEntityInterface {

    long getId();

    String getUserId();

    void setUserId(String userId);

    String getName();

    void setName(String name);

}
