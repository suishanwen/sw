package com.sw.domain.entity.autovote;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Embeddable
@XmlRootElement
public class TaskRewardPK implements Serializable {
    @Column(name = "TASK_NAME")
    private String taskName;
    @Column(name = "BACKGROUND_NO")
    private String backGroundNo;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "WORK_ID")
    private String workId;
    @Column(name = "SUB_ID")
    private String subId;

    public TaskRewardPK() {
    }

    public TaskRewardPK(String taskName, String backGroundNo, String userId, String workId, String subId) {
        this.taskName = taskName;
        this.backGroundNo = backGroundNo;
        this.userId = userId;
        this.workId = workId;
        this.subId = subId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getBackGroundNo() {
        return backGroundNo;
    }

    public void setBackGroundNo(String backGroundNo) {
        this.backGroundNo = backGroundNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskRewardPK that = (TaskRewardPK) o;

        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (backGroundNo != null ? !backGroundNo.equals(that.backGroundNo) : that.backGroundNo != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (workId != null ? !workId.equals(that.workId) : that.workId != null) return false;
        return subId != null ? subId.equals(that.subId) : that.subId == null;
    }

    @Override
    public int hashCode() {
        int result = taskName != null ? taskName.hashCode() : 0;
        result = 31 * result + (backGroundNo != null ? backGroundNo.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (workId != null ? workId.hashCode() : 0);
        result = 31 * result + (subId != null ? subId.hashCode() : 0);
        return result;
    }
}
