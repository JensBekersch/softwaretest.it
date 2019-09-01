package it.softwaretest.app.ws.siteElements.command;

import it.softwaretest.app.ws.command.Command;
import it.softwaretest.app.ws.exceptions.CouldNotCreateRecordException;
import it.softwaretest.app.ws.io.entity.impl.SingleDomElementEntity;
import it.softwaretest.app.ws.service.impl.ProjectService;
import it.softwaretest.app.ws.service.impl.SiteElementsService;
import it.softwaretest.app.ws.service.impl.SitesService;
import it.softwaretest.app.ws.shared.dto.impl.ProjectDto;
import it.softwaretest.app.ws.shared.dto.impl.SiteElementsDto;
import it.softwaretest.app.ws.shared.dto.impl.SitesDto;
import it.softwaretest.app.ws.ui.model.request.impl.CreateSiteElementsRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateSiteElements implements Command {

    private SiteElementsDto siteElementsDto;
    private CreateSiteElementsRequest createSiteElementsRequest;
    private SiteElementsService service;
    private SitesService sitesService;
    private ProjectService projectService;
    private ProjectDto projectDto;
    private SitesDto sitesDto;
    private SiteElementsService siteElementsService;

    private Document document;
    private Elements elements;

    public CreateSiteElements(
        SiteElementsDto siteElementsDto,
        CreateSiteElementsRequest createSiteElementsRequest,
        SiteElementsService service,
        SitesService sitesService,
        ProjectService projectService,
        ProjectDto projectDto,
        SitesDto sitesDto,
        SiteElementsService siteElementsService
    ) {
        this.siteElementsDto = siteElementsDto;
        this.createSiteElementsRequest = createSiteElementsRequest;
        this.service = service;
        this.sitesService = sitesService;
        this.projectService = projectService;
        this.projectDto = projectDto;
        this.sitesDto = sitesDto;
        this.siteElementsService = siteElementsService;
    }

    @Override
    public void execute() {
        this.checkAuthorization();
        this.getContent();
        this.getElements();
        this.createElementList();
        this.completeDtoInformations();
        this.persistSiteElements();
    }

    private void checkAuthorization() {
        this.sitesDto = this.sitesService.getSite(this.createSiteElementsRequest.getSiteId());
        this.projectDto = this.projectService.getProject(this.sitesDto.getProject_id());

        if (!this.projectDto.getUserId().equals(this.createSiteElementsRequest.getUserId()))
            throw new CouldNotCreateRecordException("Authentication failed!");
    }

    private void getContent() {
        try {
            this.document = Jsoup.connect(this.sitesDto.getUrl()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getElements() {
        this.elements = this.document.body().select("*");
    }

    private void createElementList() {
        List<SingleDomElementEntity> domElement = new ArrayList<>();

        for (Element element : elements) {
            SingleDomElementEntity domElementEntity = new SingleDomElementEntity();

            domElementEntity.setElementClass(element.className());
            domElementEntity.setElementId(element.id());
            domElementEntity.setElementXPath(element.cssSelector());
            domElementEntity.setElementType(element.nodeName());

            domElement.add(domElementEntity);
        }

        this.siteElementsDto.setSingleDomElements(domElement);
    }

    private void completeDtoInformations() {
        this.siteElementsDto.setSiteId(this.createSiteElementsRequest.getSiteId());
    }

    private void persistSiteElements() {
        this.siteElementsService.createSiteElements(this.siteElementsDto);
    }
}
