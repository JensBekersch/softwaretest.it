package it.softwaretest.app.ws.project.builder;

import it.softwaretest.app.ws.ui.model.response.impl.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectData {

    protected List<Project> projectList = new ArrayList<>();

    public List<Project> getProjects() {
        return this.projectList;
    }

    public void addProjectToList(Project project) {
        this.projectList.add(project);
    }

}
