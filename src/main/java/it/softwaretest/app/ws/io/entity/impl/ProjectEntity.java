package it.softwaretest.app.ws.io.entity.impl;

import it.softwaretest.app.ws.io.entity.ProjectEntityInterface;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "Project")
public class ProjectEntity implements ProjectEntityInterface, Serializable {

    private static final long serialVersionUID = -8611271466876884589L;

    @Id
    @GeneratedValue
    private long id;

    private String userId;

    private String name;

    public ProjectEntity() {}

    public ProjectEntity(long id, String userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public long getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
