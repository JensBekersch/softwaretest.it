package it.softwaretest.app.ws.shared.dto.impl;

import it.softwaretest.app.ws.io.entity.impl.UserEntity;
import it.softwaretest.app.ws.io.repository.UserRepositoryInterface;
import it.softwaretest.app.ws.shared.dto.ProjectDtoInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ProjectDto implements Serializable, ProjectDtoInterface {

    private static final long serialVersionUID = 375579645656910754L;

    private long id;
    private String userId;
    private UserEntity user;
    private String name;

    @Autowired
    UserRepositoryInterface userRepository;

    public ProjectDto() {}

    public ProjectDto(long id, String userId, UserEntity user, String name) {
        this.id = id;
        this.userId = userId;
        this.user = user;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
