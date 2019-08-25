package it.softwaretest.app.ws.site.builder.siteBuilder;

import it.softwaretest.app.ws.exceptions.NoRecordFoundException;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.service.impl.SitesService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.shared.dto.impl.SitesDto;
import it.softwaretest.app.ws.ui.model.response.impl.Site;
import org.springframework.beans.BeanUtils;

import javax.inject.Inject;

public class GetSiteBuilder implements GetSiteBuilderInterface {

    private SitesService sitesService;
    private ProjectService projectService;

    protected SiteData siteData;

    @Inject
    public GetSiteBuilder(SitesService sitesService, ProjectService projectService) {
        this.sitesService = sitesService;
        this.projectService = projectService;
        this.reset();
    }

    private void reset() {
        this.siteData = new SiteData();
    }

    @Override
    public void getSiteById(long id, String userId) {
        Site site = new Site();
        SitesDto sitesDto = this.sitesService.getSite(id);
        ProjectDto project = this.projectService.getProject(sitesDto.getProject_id());

        if (project.getUserId().equals(userId)) {
            BeanUtils.copyProperties(sitesDto, site);
            this.siteData.setSite(site);
        } else {
            throw new NoRecordFoundException("No entry was found");
        }
    }

    public SiteData getSite() {
        SiteData returnValue = this.siteData;
        this.reset();
        return returnValue;
    }

}
