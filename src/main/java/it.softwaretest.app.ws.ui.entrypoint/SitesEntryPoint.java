package it.softwaretest.app.ws.ui.entrypoint;

import it.softwaretest.app.ws.annotations.Secured;
import it.softwaretest.app.ws.site.builder.siteBuilder.GetSiteBuilder;
import it.softwaretest.app.ws.site.builder.siteBuilder.SiteDirector;
import it.softwaretest.app.ws.site.builder.siteListBuilder.GetSiteListBuilder;
import it.softwaretest.app.ws.site.builder.siteListBuilder.SiteListDirector;
import it.softwaretest.app.ws.site.command.CreateSiteCommand;
import it.softwaretest.app.ws.site.command.DeleteSiteCommand;
import it.softwaretest.app.ws.site.command.ModifySiteCommand;
import it.softwaretest.app.ws.ui.model.request.impl.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sites")
public class SitesEntryPoint {

    @Autowired
    CreateSiteCommand createSiteCommand;

    @Autowired
    SiteDirector siteDirector;

    @Autowired
    GetSiteBuilder siteBuilder;

    @Autowired
    GetSiteListBuilder siteListBuilder;

    @Autowired
    SiteListDirector siteListDirector;

    @Autowired
    ModifySiteCommand modifySiteCommand;

    @Autowired
    DeleteSiteCommand deleteSiteCommand;

    @Secured
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSite(@PathParam("id") String id, CreateSiteRequest createSiteRequest) throws Exception {

        System.out.println(createSiteRequest.getName());
        System.out.println(createSiteRequest.getProject_id());
        System.out.println(createSiteRequest.getUrl());
        createSiteRequest.setUserId(id);
        createSiteCommand.setSiteRequest(createSiteRequest);
        createSiteCommand.executeCommands();

        return Response.status(201).build();
    }

    @Secured
    @GET
    @Path("/{id}/{projectId}/{list}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSiteById(@PathParam("id") String id, @PathParam("projectId") long projectId, @PathParam("list") boolean list) {
        if(list) {
            siteListDirector.setSiteListBuilder(this.siteListBuilder);
            siteListDirector.buildSiteList(projectId, id);

            return Response.ok(this.siteListBuilder.getSiteList()).status(200).build();
        } else {
            siteDirector.setSiteBuilder(this.siteBuilder);
            siteDirector.buildSite(projectId, id);

            return Response.ok(this.siteBuilder.getSite()).status(200).build();
        }
    }

    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProject(@PathParam("id") String id, UpdateSiteRequest updateSitetRequest) throws Exception {
        updateSitetRequest.setUserId(id);
        modifySiteCommand.setUpdateRequest(updateSitetRequest);
        modifySiteCommand.executeCommands();

        return Response.status(204).build();
    }

    @Secured
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSite(@PathParam("id") String id, DeleteSiteRequest deleteSiteRequest) throws Exception {
        deleteSiteRequest.setUserId(id);
        deleteSiteCommand.setDeleteSiteRequest(deleteSiteRequest);
        deleteSiteCommand.executeCommands();

        return Response.status(204).build();
    }
}
