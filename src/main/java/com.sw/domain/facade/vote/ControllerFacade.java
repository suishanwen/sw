package com.sw.domain.facade.vote;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.sw.domain.entity.vote.Controller;
import com.sw.domain.facade.BaseFacade;
import com.sw.domain.facade.util.SequenceFacade;

import java.util.Date;
import java.util.List;

public class ControllerFacade extends BaseFacade {
    private SequenceFacade sequenceFacade;

    @Inject
    public void setSequenceFacade(SequenceFacade sequenceFacade) {
        this.sequenceFacade = sequenceFacade;
    }

    @Transactional
    public String report(String id, String workerId, Integer vm1, Integer vm2, String taskName) {
        Controller controller = entityManager.find(Controller.class, id);
        controller.setWorkerId(workerId);
        controller.setVm1(vm1);
        controller.setVm2(vm2);
        controller.setTaskName(taskName);
        controller.setOperateDateTime(new Date());
        return "ok";
    }

    @Transactional
    public Controller sync(String id) {
        Controller controller=entityManager.find(Controller.class, id);
        controller.setSyncDateTime(new Date());
        return controller;
    }

    @Transactional
    private String registerComputer(String cpu,String hdd){
        Controller controller=new Controller();
        controller.setId(sequenceFacade.nextSequence("CONTROLLER","ID"));;
        controller.setCpu(cpu);
        controller.setHdd(hdd);
        controller.setOperateDateTime(new Date());
        entityManager.persist(controller);
        return controller.getId();
    }

    @Transactional
    public String register(String cpu,String hdd) {
        List<Controller> controllerList =entityManager.createQuery("from Controller c where c.cpu=:cpu and c.hdd=:hdd")
                .setParameter("cpu",cpu).setParameter("hdd",hdd).getResultList();
        if(controllerList.size()>0){
            return controllerList.get(0).getId();
        }else{
            return registerComputer(cpu,hdd);
        }
    }

    @Transactional
    public Controller manage(Controller controller) {
        Controller controllerOld=entityManager.find(Controller.class,controller.getId());
        entityManager.remove(controllerOld);
        controller.setOperateDateTime(new Date());
        entityManager.persist(controller);
        return controller;
    }
}
