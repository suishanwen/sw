package com.sw.api.controller;

import com.sw.domain.entity.controller.Vm;
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
    @Path("g")
    @OnException("GetSingleVmFail")
    @Produces(MediaType.APPLICATION_JSON)
    public String getVmSingle(@QueryParam("u") String u){
        return VmFacade.getVmSingle(u);
    }

    @GET
    @Path("u")
    @OnException("VmUploadFail")
    @Produces(MediaType.APPLICATION_JSON)
    public String uploadVmSingle(@QueryParam("u") String u,
                                    @QueryParam("p") String p,
                                    @QueryParam("st") Integer st,
                                    @QueryParam("sc") Integer sc,
                                    @QueryParam("f") Integer f,
                                    @QueryParam("t") Integer t,
                                    @QueryParam("m") String m){
        String project=VmFacade.getVmSingle(u);
        if(project!=null&&project.equals(p)){
            VmFacade.uploadVmInfo(u,st,sc,f,t,m);
        }
        return project;
    }

    @GET
    @Path("dm")
    @OnException("SwitchDmFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void switchVmDm(@QueryParam("u") String u){
         VmFacade.switchVmDm(u);
    }
}
