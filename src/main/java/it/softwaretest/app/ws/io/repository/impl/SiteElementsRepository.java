package it.softwaretest.app.ws.io.repository.impl;

import it.softwaretest.app.ws.io.entity.impl.SiteElementsEntity;
import it.softwaretest.app.ws.io.repository.SiteElementsRepositoryInterface;
import it.softwaretest.app.ws.shared.dto.impl.SiteElementsDto;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SiteElementsRepository extends AbstractRepository implements SiteElementsRepositoryInterface {

    @Override
    public SiteElementsDto getSiteElementsById(long id) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<SiteElementsEntity> criteria = criteriaBuilder.createQuery(SiteElementsEntity.class);

        Root<SiteElementsEntity> profileRoot = criteria.from(SiteElementsEntity.class);
        criteria.select(profileRoot);
        criteria.where(criteriaBuilder.equal(profileRoot.get("id"), id));

        SiteElementsEntity siteElementsEntity = session.createQuery(criteria).getSingleResult();

        SiteElementsDto siteElementsDto = new SiteElementsDto();
        BeanUtils.copyProperties(siteElementsEntity, siteElementsDto);

        return siteElementsDto;
    }

    @Override
    public SiteElementsDto savesiteElements(SiteElementsDto siteElementsDto) {
        SiteElementsDto returnValue;

        SiteElementsEntity siteElementsEntity = new SiteElementsEntity();
        BeanUtils.copyProperties(siteElementsDto, siteElementsEntity);

        session.beginTransaction();
        session.save(siteElementsEntity);
        session.getTransaction().commit();

        returnValue = new SiteElementsDto();
        BeanUtils.copyProperties(siteElementsEntity, returnValue);

        return returnValue;
    }

    @Override
    public void updateSiteElements(SiteElementsDto siteElementsDto) {
        // not implemented yet...
    }

    @Override
    public void deleteSiteElements(SiteElementsDto siteElementsDto) {
        // not implemented yet...
    }

}
