package it.softwaretest.app.ws.io.repository;

import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;

import java.util.List;

public interface ProjectRepositoryInterface {
    void openConnection();
    ProjectDto getProjectById(long id);
    List<ProjectDto> getProjectList(String id);
    ProjectDto saveProject(ProjectDto project);
    void updateProject(ProjectDto project);
    void deleteProject(ProjectDto project);
    void closeConnection();
}
