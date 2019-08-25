package it.softwaretest.app.ws.service;

import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;

import java.util.List;

public interface ProjectServiceInterface {

    ProjectDto createProject(ProjectDto project);

    ProjectDto getProject(long id);

    List<ProjectDto> getProjectList(String id);

    void updateProject(ProjectDto projectDto);

    void deleteProject(ProjectDto projectDto);

}
