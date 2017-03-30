package com.sw.domain.entity.autovote;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CURRENT_TASK_INDEX")
public class CurrentTaskIndex {
    @Id
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "TASK_NAME")
    private String taskName;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE_TIME")
    private Date startDateTime;

    public CurrentTaskIndex() {
    }

    public CurrentTaskIndex(String userId, String taskName, Date startDateTime) {
        this.userId = userId;
        this.taskName = taskName;
        this.startDateTime = startDateTime;
    }

    public void startTask(String taskName, Date startDateTime) {
        this.taskName = taskName;
        this.startDateTime = startDateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }
}
