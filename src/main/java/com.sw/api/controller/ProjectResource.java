package com.sw.api.controller;


import com.google.inject.Inject;
import com.sw.domain.entity.controller.Project;
import com.sw.domain.facade.controller.ProjectFacade;
import com.sw.domain.util.OnException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/project")
public class ProjectResource {
    private ProjectFacade projectFacade;

    @Inject
    public ProjectResource(ProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }

    @POST
    @Path("add")
    @OnException("AddProjectFail")
    @Produces(MediaType.APPLICATION_JSON)
    public String addProject(Project project){
        return projectFacade.addProject(project);
    }

    @POST
    @Path("delete")
    @OnException("DeleteProjectFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProject(Integer id){
        projectFacade.deleteProject(id);
    }

    @POST
    @OnException("GetProjectFail")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectList(String inputCode){
        return projectFacade.getProjectByInputCpde(inputCode);
    }

}
