package it.softwaretest.app.ws.project.builder;

public class ProjectDirector {

    private GetProjectBuilder projectBuilder;

    public void setBuilder(GetProjectBuilder projectBuilder) {
        this.projectBuilder = projectBuilder;
    }

    public void buildProjectList(String id) {
        this.projectBuilder.getProjectDataAndCreateList(id);
    }
}
