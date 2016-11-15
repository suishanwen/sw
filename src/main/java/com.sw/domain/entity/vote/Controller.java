package com.sw.domain.entity.vote;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Controller {
    @Id
    private String id;
    private String identify;
    private String workerId;
    private Integer vm1;
    private Integer vm2;
    private Integer idSwitch;
    private Integer tailSwitch;
    private Integer overTime;
    private String taskName;
    private String cpu;
    private String hdd;
    private String arrDrop;
    private Integer sortNo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date operateDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date syncDateTime;

    public Controller() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public Integer getVm1() {
        return vm1;
    }

    public void setVm1(Integer vm1) {
        this.vm1 = vm1;
    }

    public Integer getVm2() {
        return vm2;
    }

    public void setVm2(Integer vm2) {
        this.vm2 = vm2;
    }

    public Integer getIdSwitch() {
        return idSwitch;
    }

    public void setIdSwitch(Integer idSwitch) {
        this.idSwitch = idSwitch;
    }

    public Integer getTailSwitch() {
        return tailSwitch;
    }

    public void setTailSwitch(Integer tailSwitch) {
        this.tailSwitch = tailSwitch;
    }

    public Integer getOverTime() {
        return overTime;
    }

    public void setOverTime(Integer overTime) {
        this.overTime = overTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getArrDrop() {
        return arrDrop;
    }

    public void setArrDrop(String arrDrop) {
        this.arrDrop = arrDrop;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Date getOperateDateTime() {
        return operateDateTime;
    }

    public void setOperateDateTime(Date operateDateTime) {
        this.operateDateTime = operateDateTime;
    }

    public Date getSyncDateTime() {
        return syncDateTime;
    }

    public void setSyncDateTime(Date syncDateTime) {
        this.syncDateTime = syncDateTime;
    }
}
