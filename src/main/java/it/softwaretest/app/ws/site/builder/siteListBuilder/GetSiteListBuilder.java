package it.softwaretest.app.ws.site.builder.siteListBuilder;

import it.softwaretest.app.ws.exceptions.NoRecordFoundException;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.service.impl.SitesService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.shared.dto.impl.SitesDto;
import it.softwaretest.app.ws.site.builder.siteBuilder.GetSiteBuilderInterface;
import it.softwaretest.app.ws.site.builder.siteBuilder.SiteData;
import it.softwaretest.app.ws.ui.model.response.impl.Site;
import org.springframework.beans.BeanUtils;

import javax.inject.Inject;
import java.util.List;

public class GetSiteListBuilder implements GetSiteListBuilderInterface {

    private SitesService sitesService;
    private ProjectService projectService;

    protected SiteListData siteList;

    @Inject
    public GetSiteListBuilder(SitesService sitesService, ProjectService projectService) {
        this.sitesService = sitesService;
        this.projectService = projectService;
        this.reset();
    }

    private void reset() {
        this.siteList = new SiteListData();
    }

    @Override
    public void getSiteListByProjectId(long id, String userId) {
        ProjectDto project = this.projectService.getProject(id);

        if (project.getUserId().equals(userId)) {
            List<SitesDto> sitesDtoList = this.sitesService.getSitesList(project.getId());
            for(SitesDto siteDto: sitesDtoList) {
                Site site = new Site();
                BeanUtils.copyProperties(siteDto, site);
                this.siteList.addSite(site);
            }
        } else {
            throw new NoRecordFoundException("No entry was found");
        }
    }

    public List<Site> getSiteList() {
        List<Site> returnValue = this.siteList.getSiteList();
        this.reset();
        return returnValue;
    }

}
