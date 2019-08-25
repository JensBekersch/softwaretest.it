package it.softwaretest.app.ws.io.repository.impl;

import it.softwaretest.app.ws.io.entity.impl.ProjectEntity;
import it.softwaretest.app.ws.io.repository.ProjectRepositoryInterface;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import org.springframework.beans.BeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class ProjectRepository extends AbstractRepository implements ProjectRepositoryInterface {


    @Override
    public ProjectDto getProjectById(long id) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<ProjectEntity> criteria = criteriaBuilder.createQuery(ProjectEntity.class);

        Root<ProjectEntity> profileRoot = criteria.from(ProjectEntity.class);
        criteria.select(profileRoot);
        criteria.where(criteriaBuilder.equal(profileRoot.get("id"), id));

        ProjectEntity projectEntity = session.createQuery(criteria).getSingleResult();

        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(projectEntity, projectDto);

        return projectDto;
    }

    @Override
    public List<ProjectDto> getProjectList(String id) {
        List<ProjectDto> projects = new ArrayList<>();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<ProjectEntity> criteria = criteriaBuilder.createQuery(ProjectEntity.class);

        Root<ProjectEntity> projectRoot = criteria.from(ProjectEntity.class);
        criteria.select(projectRoot);
        criteria.where(criteriaBuilder.equal(projectRoot.get("userId"), id));

        List<ProjectEntity> projectEntities = session.createQuery(criteria).list();

        for (ProjectEntity project: projectEntities) {
            ProjectDto projectDto = new ProjectDto();
            BeanUtils.copyProperties(project, projectDto);
            projects.add(projectDto);
        }

        return projects;
    }

    @Override
    public ProjectDto saveProject(ProjectDto project) {
        ProjectDto returnValue;

        ProjectEntity projectEntity = new ProjectEntity();
        BeanUtils.copyProperties(project, projectEntity);

        session.beginTransaction();
        session.save(projectEntity);
        session.getTransaction().commit();

        returnValue = new ProjectDto();
        BeanUtils.copyProperties(projectEntity, returnValue);

        return returnValue;
    }

    @Override
    public void updateProject(ProjectDto project) {
        ProjectEntity projectEntity = new ProjectEntity();
        BeanUtils.copyProperties(project, projectEntity);

        session.beginTransaction();
        session.update(projectEntity);
        session.getTransaction().commit();
    }

    @Override
    public void deleteProject(ProjectDto project) {
        ProjectEntity projectEntity = new ProjectEntity();
        BeanUtils.copyProperties(project, projectEntity);

        session.beginTransaction();
        session.delete(projectEntity);
        session.getTransaction().commit();
    }
}
