package com.sw.domain.entity.autovote;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.Assigned;

import javax.persistence.*;

@Entity
@Table(name = "TASK_INDEX")
public class TaskIndex {
    @Id
    @Column(name = "TASK_NAME")
    private String taskName;
    @Column(name = "TASK_CATE")
    private int taskCate;
    @Column(name = "IP_TYPE")
    private int ipType;
    @Column(name = "DOWNLOAD_ADDRESS")
    private String downloadAddress;
    @Column(name = "IS_VALID")
    private int isValid;

    public TaskIndex() {
    }

    public TaskIndex(String taskName, int taskCate, int ipType, String downloadAddress, int isValid) {
        this.taskName = taskName;
        this.taskCate = taskCate;
        this.ipType = ipType;
        this.downloadAddress = downloadAddress;
        this.isValid = isValid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskCate() {
        return taskCate;
    }

    public void setTaskCate(int taskCate) {
        this.taskCate = taskCate;
    }

    public int getIpType() {
        return ipType;
    }

    public void setIpType(int ipType) {
        this.ipType = ipType;
    }

    public String getDownloadAddress() {
        return downloadAddress;
    }

    public void setDownloadAddress(String downloadAddress) {
        this.downloadAddress = downloadAddress;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }
}
