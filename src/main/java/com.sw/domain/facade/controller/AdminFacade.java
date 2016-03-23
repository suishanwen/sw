package com.sw.domain.facade.controller;


import com.google.inject.persist.Transactional;
import com.sw.domain.entity.controller.Admin;
import com.sw.domain.facade.BaseFacade;

import java.util.List;

public class AdminFacade extends BaseFacade{

    @Transactional
    public void addAdmin(Admin admin){
        Admin a=new Admin();
        a.setAdmin(admin.getAdmin());
        a.setPassword(admin.getPassword());
        a.setState("1");
        entityManager.persist(a);
    }

    @Transactional
    public void deleteAdmin(Integer id){
        Admin p=entityManager.find(Admin.class,id);
        entityManager.remove(p);
    }

    @Transactional
    public void updateAdmin(Admin admin){
        Admin a=entityManager.find(Admin.class,admin.getId());
        a.setAdmin(admin.getAdmin());
        a.setPassword(admin.getPassword());
        entityManager.persist(a);
    }


    public List<Admin> getAllAdmin(){
        return entityManager.createQuery("select a from Admin a").getResultList();
    }

    public Admin loginAdmin(Admin admin){
        try {
            Admin a=(Admin)entityManager.createQuery("select a from Admin a where a.admin=:admin and a.password=:password")
                    .setParameter("admin", admin.getAdmin())
                    .setParameter("password", admin.getPassword())
                    .getSingleResult();
            return a;
        }catch (Exception e){
            System.out.println("UserTryLoginFailed");
            return null;
        }
    }

    public String getEmployeeId(String userName) {
        List<Admin> admins = entityManager.createQuery("select a from Admin a where a.admin=:admin")
                .setParameter("admin", userName)
                .getResultList();
        if(admins.size()>0){
            String employeeNo=admins.get(0).getEmployeeNo();
            return employeeNo.split("|")[0];
        }
        return "";
    }

    public void changeEmployeeId(String admin,String employeeNo){
        List<Admin> admins = entityManager.createQuery("select a from Admin a where a.admin=:admin")
                .setParameter("admin", admin)
                .getResultList();
        if(admins.size()>0){
            Admin a=admins.get(0);
            a.setEmployeeNo(employeeNo);
            entityManager.merge(a);
        }
    }
}
