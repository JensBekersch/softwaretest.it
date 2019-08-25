package it.softwaretest.app.ws.io.repository.impl;

import it.softwaretest.app.ws.io.entity.impl.ProjectEntity;
import it.softwaretest.app.ws.io.entity.impl.SitesEntity;
import it.softwaretest.app.ws.io.repository.SitesRepositoryInterface;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.shared.dto.impl.SitesDto;
import org.springframework.beans.BeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SitesRepository extends AbstractRepository implements SitesRepositoryInterface {

    @Override
    public SitesDto getSiteById(long id) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<SitesEntity> criteria = criteriaBuilder.createQuery(SitesEntity.class);

        Root<SitesEntity> profileRoot = criteria.from(SitesEntity.class);
        criteria.select(profileRoot);
        criteria.where(criteriaBuilder.equal(profileRoot.get("id"), id));

        SitesEntity sitesEntity = session.createQuery(criteria).getSingleResult();

        SitesDto sitesDto = new SitesDto();
        BeanUtils.copyProperties(sitesEntity, sitesDto);

        return sitesDto;
    }

    @Override
    public List<SitesDto> getSitesByProjectId(long id) {
        List<SitesDto> sitesDtoList = new ArrayList<>();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<SitesEntity> criteria = criteriaBuilder.createQuery(SitesEntity.class);

        Root<SitesEntity> profileRoot = criteria.from(SitesEntity.class);
        criteria.select(profileRoot);
        criteria.where(criteriaBuilder.equal(profileRoot.get("project_id"), id));

        List<SitesEntity> sitesEntity = session.createQuery(criteria).list();

        for (SitesEntity sites: sitesEntity) {
            SitesDto sitesDto = new SitesDto();
            BeanUtils.copyProperties(sites, sitesDto);
            sitesDtoList.add(sitesDto);
        }
        return sitesDtoList;
    }

    @Override
    public SitesDto saveSite(SitesDto sitesDto) {
        SitesDto returnValue;

        SitesEntity sitesEntity = new SitesEntity();
        BeanUtils.copyProperties(sitesDto, sitesEntity);

        session.beginTransaction();
        session.save(sitesEntity);
        session.getTransaction().commit();

        returnValue = new SitesDto();
        BeanUtils.copyProperties(sitesEntity, returnValue);

        return returnValue;
    }

    @Override
    public void updateSite(SitesDto sitesDto) {
        SitesEntity sitesEntity = new SitesEntity();
        BeanUtils.copyProperties(sitesDto, sitesEntity);

        session.beginTransaction();
        session.update(sitesEntity);
        session.getTransaction().commit();
    }

    @Override
    public void deleteSite(SitesDto sitesDto) {
        SitesEntity sitesEntity = new SitesEntity();
        BeanUtils.copyProperties(sitesDto, sitesEntity);

        session.beginTransaction();
        session.delete(sitesEntity);
        session.getTransaction().commit();
    }
}
