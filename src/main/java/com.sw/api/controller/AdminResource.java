package com.sw.api.controller;

import com.sw.domain.entity.controller.Admin;
import com.sw.domain.facade.controller.AdminFacade;
import com.sw.domain.util.OnException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/admin")
public class AdminResource {
    private AdminFacade adminFacade;
    @Inject
    public AdminResource(AdminFacade adminFacade) {
        this.adminFacade = adminFacade;
    }

    @POST
    @OnException("GetAdminListFail")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Admin> getAllAdmin(){
        return adminFacade.getAllAdmin();
    }

    @POST
    @Path("add")
    @OnException("AddAdminFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void addAdmin(Admin admin){
        adminFacade.addAdmin(admin);
    }

    @POST
    @Path("update")
    @OnException("UpdateAdminFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateAdmin(Admin admin){
        adminFacade.updateAdmin(admin);
    }

    @POST
    @Path("delete")
    @OnException("DeleteAdminFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAdmin(Integer adminId){
        adminFacade.deleteAdmin(adminId);
    }

    @POST
    @Path("login")
    @OnException("AdminLoginFail")
    @Produces(MediaType.APPLICATION_JSON)
    public Admin loginAdmin(Admin admin){
         return adminFacade.loginAdmin(admin);
    }
}
