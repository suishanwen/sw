package com.sw.domain.facade.controller;


import com.google.inject.persist.Transactional;
import com.sw.domain.entity.controller.Project;
import com.sw.domain.facade.BaseFacade;

import java.util.Date;
import java.util.List;

public class ProjectFacade extends BaseFacade{

    @Transactional
    public String addProject(Project project){
        if(checkProjectExists(project.getName(),project.getType())){
            return "1";
        };
        project.setCreateTime(new Date());
        entityManager.persist(project);
        return "0";
    }

    @Transactional
    public void deleteProject(Integer id){
        Project project=entityManager.find(Project.class,id);
        entityManager.persist(project);
    }

    public List<Project> getProjectByInputCpde(String inputCode){
        return entityManager.createQuery("select p from Project p where p.name like :inputCode")
                .setParameter("inputCode","%"+inputCode+"%")
                .setMaxResults(10)
                .getResultList();
    }

    public boolean checkProjectExists(String name,String type){
        List list=entityManager.createQuery("select p from Project p where p.name=:name and p.type=:type")
                .setParameter("name",name).setParameter("type",type).getResultList();
        if(list.size()>0){
            return true;
        }else{
            return false;
        }
    }
}
