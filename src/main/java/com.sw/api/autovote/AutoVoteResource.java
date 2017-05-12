package com.sw.api.autovote;

import com.sw.domain.facade.autovote.AutoVoteFacade;
import com.sw.domain.util.OnException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Path("/auto-vote")
public class AutoVoteResource {
    private AutoVoteFacade autoVoteFacade;

    @Inject
    public AutoVoteResource(AutoVoteFacade autoVoteFacade) {
        this.autoVoteFacade = autoVoteFacade;
    }

    @GET
    @OnException("getAllTaskIndexFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTaskIndex() {
        return Response.ok().entity(autoVoteFacade.getValidTaskIndex()).build();
    }


    @GET
    @Path("task-today")
    @OnException("getTaskTodayFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTaskToday() {
        return Response.ok().entity(autoVoteFacade.getTaskInfoToday()).build();
    }

    @GET
    @Path("is-task-valid")
    @OnException("checkIsTaskValidFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isValidTask(@QueryParam("taskName") String taskName, @QueryParam("downloadAddress") String downloadAddress) {
        return Response.ok().entity(autoVoteFacade.isValidTask(taskName, downloadAddress)).build();
    }

    @GET
    @Path("best-task")
    @OnException("getBestTaskFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBestTask() {
        return Response.ok().entity(autoVoteFacade.getBestTask()).build();
    }

    @GET
    @Path("set-best-task")
    @OnException("setBestTaskFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setBestTask(@QueryParam("taskName") String taskName,
                                @QueryParam("backgroundNo") String backgroundNo,
                                @QueryParam("idCate") String idCate,
                                @QueryParam("price") BigDecimal price) {
        return Response.ok().entity(autoVoteFacade.setBestTask(taskName, backgroundNo, idCate, price)).build();
    }

    @GET
    @Path("add-task-index")
    @OnException("addTaskIndexFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTaskIndex(@QueryParam("taskName") String taskName,
                                 @QueryParam("taskCate") int taskCate,
                                 @QueryParam("ipType") int ipType,
                                 @QueryParam("downloadAddress") String downloadAddress) {
        return Response.ok(autoVoteFacade.addTaskIndex(taskName, taskCate, ipType, downloadAddress)).build();
    }


    @GET
    @Path("update-task-index")
    @OnException("updateTaskIndexFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTaskIndexInfo(@QueryParam("taskName") String taskName,
                                        @QueryParam("isValid") int isValid) {
        return Response.ok(autoVoteFacade.updateTaskIndex(taskName, isValid)).build();
    }

    @GET
    @Path("add-update-background")
    @OnException("addOrUpdateTaskBackgroundFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTaskBackground(@QueryParam("taskName") String taskName,
                                      @QueryParam("backgroundNo") String backgroundNo,
                                      @QueryParam("quantityRequire") BigDecimal quantityRequire,
                                      @QueryParam("quantityFinished") BigDecimal quantityFinished,
                                      @QueryParam("idActive") int idActive,
                                      @QueryParam("idTotal") int idTotal,
                                      @QueryParam("idAllow") int idAllow,
                                      @QueryParam("idCate") String idCate,
                                      @QueryParam("price") BigDecimal price
    ) {
        return Response.ok(autoVoteFacade.addOrUpdateTaskBackground(taskName, backgroundNo, quantityRequire, quantityFinished, idActive, idTotal, idAllow, idCate, price)).build();
    }

    @GET
    @Path("start-task")
    @OnException("startTaskFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startTaskByUser(@QueryParam("userId") String userId,
                                    @QueryParam("taskName") String taskName) {
        return Response.ok(autoVoteFacade.startTask(userId, taskName)).build();
    }

    @GET
    @Path("upload-task-reward")
    @OnException("startTaskFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadTaskReward(@QueryParam("taskName") String taskName,
                                     @QueryParam("backGroundNo") String backGroundNo,
                                     @QueryParam("userId") String userId,
                                     @QueryParam("workId") String workId,
                                     @QueryParam("subId") String subId,
                                     @QueryParam("quantity") int quantity
    ) {
        return Response.ok(autoVoteFacade.uploadTaskReward(taskName, backGroundNo, userId, workId, subId, quantity)).build();
    }
}
