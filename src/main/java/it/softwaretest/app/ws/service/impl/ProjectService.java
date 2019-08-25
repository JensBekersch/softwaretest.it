package it.softwaretest.app.ws.service.impl;

import it.softwaretest.app.ws.exceptions.CouldNotDeleteRecordException;
import it.softwaretest.app.ws.exceptions.CouldNotUpdateRecordException;
import it.softwaretest.app.ws.exceptions.NoRecordFoundException;
import it.softwaretest.app.ws.io.repository.impl.ProjectRepository;
import it.softwaretest.app.ws.service.ProjectServiceInterface;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import java.util.List;

public class ProjectService implements ProjectServiceInterface {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectDto createProject(ProjectDto project) {
        ProjectDto returnValue;

        try {
            this.projectRepository.openConnection();
            returnValue = this.projectRepository.saveProject(project);
        } finally {
            this.projectRepository.closeConnection();
        }

        return returnValue;
    }

    @Override
    public ProjectDto getProject(long id) {
        ProjectDto projectDto;

        try {
            this.projectRepository.openConnection();
            projectDto = this.projectRepository.getProjectById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessageDefinitions.NO_RECORD_FOUND.getErrorMessage());
        } finally {
            this.projectRepository.closeConnection();
        }

        return projectDto;
    }

    @Override
    public List<ProjectDto> getProjectList(String id) {
        List<ProjectDto> projectList;

        try {
            this.projectRepository.openConnection();
            projectList = this.projectRepository.getProjectList(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessageDefinitions.NO_RECORD_FOUND.getErrorMessage());
        } finally {
            this.projectRepository.closeConnection();
        }

        return projectList;
    }

    @Override
    public void updateProject(ProjectDto projectDto) {
        try {
            this.projectRepository.openConnection();
            this.projectRepository.updateProject(projectDto);
        } catch (Exception ex) {
            throw new CouldNotUpdateRecordException(ex.getMessage());
        } finally {
            this.projectRepository.closeConnection();
        }
    }

    @Override
    public void deleteProject(ProjectDto projectDto) {
        try {
            this.projectRepository.openConnection();
            this.projectRepository.deleteProject(projectDto);
        } catch (Exception ex) {
            throw new CouldNotDeleteRecordException(ex.getMessage());
        } finally {
            this.projectRepository.closeConnection();;
        }

        try {
            projectDto = getProject(projectDto.getId());
        } catch (NoRecordFoundException ex) {
            projectDto = null;
        }

        if (projectDto != null)
            throw new CouldNotDeleteRecordException(ErrorMessageDefinitions.COULD_NOT_DELETE_RECORD.getErrorMessage());
    }

}
