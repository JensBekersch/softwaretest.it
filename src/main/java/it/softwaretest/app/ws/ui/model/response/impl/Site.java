package it.softwaretest.app.ws.ui.model.response.impl;

import it.softwaretest.app.ws.ui.model.response.SiteInterface;

public class Site implements SiteInterface {

    private long id;
    private long project_id;
    private String name;
    private String url;

    public Site() {}

    public Site(long id, long project_id, String name, String url) {
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
