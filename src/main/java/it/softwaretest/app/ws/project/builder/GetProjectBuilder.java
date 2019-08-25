package it.softwaretest.app.ws.project.builder;

import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.ui.model.response.impl.Project;
import org.springframework.beans.BeanUtils;

import javax.inject.Inject;
import java.util.List;

public class GetProjectBuilder implements GetProjectBuilderInterface {

    private ProjectService projectService;

    protected ProjectData projectData;

    @Inject
    public GetProjectBuilder(ProjectService projectService) {
        this.projectService = projectService;
        this.reset();
    }

    private void reset() {
        this.projectData = new ProjectData();
    }

    @Override
    public void getProjectDataAndCreateList(String id) {
        List<ProjectDto> projectList = this.projectService.getProjectList(id);

        for(ProjectDto project: projectList) {
            Project transferData = new Project();
            BeanUtils.copyProperties(project, transferData);
            this.projectData.addProjectToList(transferData);
        }
    }

    public ProjectData getProjectList() {
        ProjectData projectData = this.projectData;
        this.reset();
        return projectData;
    }

}
