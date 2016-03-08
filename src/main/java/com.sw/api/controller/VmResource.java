package com.sw.api.controller;

import com.sw.domain.entity.controller.Admin;
import com.sw.domain.entity.controller.Vm;
import com.sw.domain.facade.controller.AdminFacade;
import com.sw.domain.facade.controller.VmFacade;
import com.sw.domain.util.OnException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/vm")
public class VmResource {
    private VmFacade VmFacade;
    @Inject
    public VmResource(VmFacade VmFacade) {
        this.VmFacade = VmFacade;
    }

    @POST
    @OnException("GetVmListFail")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vm> getAllVm(String admin){
        return VmFacade.getAllVms(admin);
    }



    @POST
    @Path("add")
    @OnException("AddVmFail")
    @Produces(MediaType.APPLICATION_JSON)
    public String addVm(Vm vm) {
        return VmFacade.addVm(vm);
    }

    @POST
    @Path("delete")
    @OnException("DeleteVmFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteVm(Integer id) {
        VmFacade.deleteVm(id);
    }

    @POST
    @Path("update")
    @OnException("UpdateVmStateFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateState(Vm vm) {
        VmFacade.updateVmState(vm);
    }



    @POST
    @Path("change-project-all")
    @OnException("ChangeAllVmFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void changeProjectAll(@QueryParam("admin") String admin,@QueryParam("project") String project ){
        VmFacade.changeProjectAll(admin,project);
    }

    @POST
    @Path("change-project-single")
    @OnException("ChangeSingleVmFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void changeProjectSingle(@QueryParam("unique") String unique,@QueryParam("project") String project ){
        VmFacade.changeProjectSingle(unique,project);
    }

    @GET
    @Path("get")
    @OnException("GetSingleVmFail")
    @Produces(MediaType.APPLICATION_JSON)
    public String getVmSingle(@QueryParam("unique") String unique){
        return VmFacade.getVmSingle(unique);
    }

    @GET
    @Path("upload")
    @OnException("VmUploadFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void changeProjectSingle(@QueryParam("unique") String unique,
                                    @QueryParam("project") String project,
                                    @QueryParam("state") Integer state,
                                    @QueryParam("success") Integer success,
                                    @QueryParam("fail") Integer fail,
                                    @QueryParam("timeout") Integer timeout,
                                    @QueryParam("message") String message){
        VmFacade.uploadVmInfo(unique,project,state,success,fail,timeout,message);
    }
}
