package com.sw.domain.entity.controller;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Vm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String admin;
    private Integer sortNo;
    private Integer state;//-1 stop,0 receive,1 busy,2 wait
    private String project;
    private Integer success;
    private Integer fail;
    private Integer timeout;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportTime;

    public Vm() {
    }

    public Vm(String admin, Integer sortNo) {
        this.admin = admin;
        this.sortNo = sortNo;
    }

    public Vm(String admin, Integer sortNo, Integer state, String project, Integer success, Integer fail, Integer timeout, String message, Date startTime, Date reportTime) {
        this.admin = admin;
        this.sortNo = sortNo;
        this.state = state;
        this.project = project;
        this.success = success;
        this.fail = fail;
        this.timeout = timeout;
        this.message = message;
        this.startTime = startTime;
        this.reportTime = reportTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
}
