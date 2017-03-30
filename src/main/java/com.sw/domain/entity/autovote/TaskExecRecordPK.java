package com.sw.domain.entity.autovote;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@XmlRootElement
public class TaskExecRecordPK implements Serializable{
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "TASK_NAME")
    private String taskName;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE_TIME")
    private Date startDateTime;
    public TaskExecRecordPK() {
    }

    public TaskExecRecordPK(String userId, String taskName, Date startDateTime) {
        this.userId = userId;
        this.taskName = taskName;
        this.startDateTime = startDateTime;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskExecRecordPK that = (TaskExecRecordPK) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        return startDateTime != null ? startDateTime.equals(that.startDateTime) : that.startDateTime == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (startDateTime != null ? startDateTime.hashCode() : 0);
        return result;
    }
}
