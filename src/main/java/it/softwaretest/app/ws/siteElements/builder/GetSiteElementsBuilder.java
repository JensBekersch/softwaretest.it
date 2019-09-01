package it.softwaretest.app.ws.siteElements.builder;

import it.softwaretest.app.ws.service.impl.SiteElementsService;
import it.softwaretest.app.ws.shared.dto.impl.SiteElementsDto;
import it.softwaretest.app.ws.ui.model.response.impl.SiteElements;
import org.springframework.beans.BeanUtils;

import javax.inject.Inject;

public class GetSiteElementsBuilder implements GetElementsBuilderInterface {

    private SiteElements siteElements;
    private SiteElementsDto siteElementsDto;
    private SiteElementsService siteElementsService;

    protected SiteElementsData siteElementsData;

    @Inject
    public GetSiteElementsBuilder(SiteElements siteElements, SiteElementsDto siteElementsDto,  SiteElementsService siteElementsService) {
        this.siteElements = siteElements;
        this.siteElementsDto = siteElementsDto;
        this.siteElementsService = siteElementsService;
        this.reset();
    }

    private void reset() {
        this.siteElementsData = new SiteElementsData();
    }

    @Override
    public void getSiteDataAndCopyToViewModel(long id) {
        this.siteElementsDto = this.siteElementsService.getElements(id);
        BeanUtils.copyProperties(this.siteElementsDto, this.siteElements);

        this.siteElementsData.setSiteElements(this.siteElements);
    }

    @Override
    public SiteElementsData getSiteElementsData() {
        SiteElementsData buildedSiteElementsData = this.siteElementsData;
        this.reset();
        return buildedSiteElementsData;

    }

}
