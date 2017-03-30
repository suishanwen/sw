package com.sw.domain.entity.autovote;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Embeddable
@XmlRootElement
public class TaskVsBackgroundPK implements Serializable {
    @Column(name = "TASK_NAME")
    private String taskName;
    @Column(name = "BACKGROUND_NO")
    private String backgroundNo;

    public TaskVsBackgroundPK() {
    }

    public TaskVsBackgroundPK(String taskName, String backgroundNo) {
        this.taskName = taskName;
        this.backgroundNo = backgroundNo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getBackgroundNo() {
        return backgroundNo;
    }

    public void setBackgroundNo(String backgroundNo) {
        this.backgroundNo = backgroundNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskVsBackgroundPK that = (TaskVsBackgroundPK) o;

        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        return backgroundNo != null ? backgroundNo.equals(that.backgroundNo) : that.backgroundNo == null;
    }

    @Override
    public int hashCode() {
        int result = taskName != null ? taskName.hashCode() : 0;
        result = 31 * result + (backgroundNo != null ? backgroundNo.hashCode() : 0);
        return result;
    }
}
