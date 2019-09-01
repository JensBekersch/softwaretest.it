package it.softwaretest.app.ws.ui.entrypoint;

import it.softwaretest.app.ws.annotations.Secured;
import it.softwaretest.app.ws.siteElements.builder.GetSiteElementsBuilder;
import it.softwaretest.app.ws.siteElements.builder.SiteElementsDirector;
import it.softwaretest.app.ws.siteElements.command.CreateSiteElementsCommand;
import it.softwaretest.app.ws.siteElements.command.DeleteSiteElementsCommand;
import it.softwaretest.app.ws.ui.model.request.impl.CreateSiteElementsRequest;
import it.softwaretest.app.ws.ui.model.request.impl.GetSiteElements;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/elements")
public class SiteElementsEntryPoint {

    @Autowired
    CreateSiteElementsCommand createSiteElementsCommand;

    @Autowired
    SiteElementsDirector siteElementsDirector;

    @Autowired
    GetSiteElementsBuilder siteElementsBuilder;

    @Autowired
    DeleteSiteElementsCommand deleteSiteElementsCommand;

    @Secured
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSiteElements(@PathParam("id") String id, CreateSiteElementsRequest createSiteElementsRequest) throws Exception {
        createSiteElementsRequest.setUserId(id);
        createSiteElementsCommand.setCreateSiteElementsRequest(createSiteElementsRequest);
        createSiteElementsCommand.executeCommands();

        return Response.status(201).build();
    }

    @Secured
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjects(@PathParam("id") String id, GetSiteElements siteElements) {
        siteElementsDirector.setBuilder(this.siteElementsBuilder);
        siteElementsDirector.buildSiteElemets(siteElements.getSiteId());

        return Response.ok(this.siteElementsBuilder.getSiteElementsData()).status(200).build();
    }

}
