package com.sw.domain.facade.controller;


import com.google.inject.persist.Transactional;
import com.sw.domain.entity.controller.Vm;
import com.sw.domain.facade.BaseFacade;

import java.util.Date;
import java.util.List;

public class VmFacade extends BaseFacade{

    @Transactional
    public String addVm(Vm vm){
        if(checkVmExists(vm)){
            return "1";
        };
        vm.setState(0);
        vm.setMessage(vm.getSortNo()+" create success");
        vm.setReportTime(new Date());
        entityManager.persist(vm);
        return "0";
    }

    @Transactional
    public void deleteVm(Integer id){
        Vm vm=entityManager.find(Vm.class,id);
        entityManager.remove(vm);
    }

    @Transactional
    public Vm updateVmState(Vm vm){
        Vm virtualMachine=entityManager.find(Vm.class,vm.getId());
        virtualMachine.setState(vm.getState());
        return entityManager.merge(vm);
    }

    public boolean checkVmExists(Vm vm){
        List<Vm> vms=entityManager.createQuery("select v from Vm v where v.admin=:admin and v.sortNo=:sortNo")
                .setParameter("admin", vm.getAdmin()).setParameter("sortNo",vm.getSortNo()).getResultList();
        if(vms.size()>0){
            return true;
        }else{
            return false;
        }
    }

    public List<Vm> getAllVms(String admin){
        return entityManager.createQuery("select v from Vm v where v.admin=:admin order by v.sortNo").setParameter("admin", admin).getResultList();
    }

    @Transactional
    public void changeProjectAll(String admin,String project){
        entityManager.createQuery("update Vm set project=:project,success=0,fail=0,timeout=0,message=null,startTime=:date,reportTime=null where admin=:admin and state<>-1")
                .setParameter("admin",admin).setParameter("project",project).setParameter("date",new Date()).executeUpdate();
    }

    @Transactional
    public void changeProjectSingle(String uniqueIdentification,String project){
        Vm vm=getVm(uniqueIdentification);
        Date date=new Date();
        vm.setProject(project);
        vm.setSuccess(0);
        vm.setFail(0);
        vm.setTimeout(0);
        vm.setStartTime(date);
        vm.setReportTime(null);
        vm.setMessage(null);
        entityManager.merge(vm);
    }

    public Vm getVm(String uniqueIdentification){
        String admin=uniqueIdentification.split("-")[0];
        Integer sortNo=Integer.parseInt(uniqueIdentification.split("-")[1]);
        return (Vm)entityManager.createQuery("select v from Vm v where v.admin=:admin and v.sortNo=:sortNo")
                .setParameter("admin",admin).setParameter("sortNo",sortNo).getSingleResult();
    }



    @Transactional
    public void uploadVmInfo(String uniqueIdentification,Integer state,Integer success,Integer fail,Integer timeout,String message){
        Vm vm=getVm(uniqueIdentification);
        if(state!=null){
            vm.setState(state);
        }
        if(success!=null){
            vm.setSuccess(success);
        }
        if(fail!=null){
            vm.setFail(fail);
        }
        if(timeout!=null){
            vm.setTimeout(timeout);
        }
        if(message!=null){
            vm.setMessage(message);
        }
        vm.setReportTime(new Date());
        entityManager.merge(vm);
    }

    public String getVmSingle(String uniqueIdentification){
        Vm vm=getVm(uniqueIdentification);
        return vm.getProject();
    }
}
