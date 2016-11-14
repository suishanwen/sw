package com.sw.api.vote;

import com.sw.domain.entity.vote.Controller;
import com.sw.domain.facade.vote.ControllerFacade;
import com.sw.domain.util.OnException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/controller")
public class ControllerResource {
    private ControllerFacade controllerFacade;

    @Inject
    public ControllerResource(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    @GET
    @Path("report")
    @OnException("reportFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportController(@QueryParam("id") String id,
    @QueryParam("workerId") String workerId,
    @QueryParam("vm1") Integer vm1,
    @QueryParam("vm2") Integer vm2,
    @QueryParam("taskName") String taskName) {
        return Response.ok().entity(controllerFacade.report(id,workerId,vm1,vm2,taskName)).build();
    }

    @GET
    @Path("sync")
    @OnException("syncFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response syncController(@QueryParam("id") String id) {
        return Response.ok().entity(controllerFacade.sync(id)).build();
    }


    @GET
    @Path("register")
    @OnException("registerFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerController(@QueryParam("cpu") String cpu,@QueryParam("hdd") String hdd) {
        return Response.ok().entity(controllerFacade.register(cpu,hdd)).build();
    }


    @POST
    @Path("manage")
    @OnException("manageFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response manageController(Controller controller) {
        return Response.ok().entity(controllerFacade.manage(controller)).build();
    }

}
