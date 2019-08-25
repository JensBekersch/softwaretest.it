package it.softwaretest.app.ws.io.entity.impl;

import it.softwaretest.app.ws.io.entity.SitesEntityInterface;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Sites")
public class SitesEntity implements SitesEntityInterface, Serializable {

    private static final long serialVersionUID = 7179361023922982148L;

    @Id
    @GeneratedValue
    private long id;

    private long project_id;

    private String name;

    private String url;

    public SitesEntity() {}

    public SitesEntity(long id, long project_id, String name, String url) {
        this.id = id;
        this.project_id = project_id;
        this.name = name;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
