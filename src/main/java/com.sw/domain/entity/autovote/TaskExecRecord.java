package com.sw.domain.entity.autovote;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TASK_EXEC_RECORD")
public class TaskExecRecord {
    @Id
    private TaskExecRecordPK id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STOP_DATE_TIME")
    private Date stopDateTime;

    public TaskExecRecord() {
    }

    public TaskExecRecord(TaskExecRecordPK id, Date stopDateTime) {
        this.id = id;
        this.stopDateTime = stopDateTime;
    }

    public TaskExecRecordPK getId() {
        return id;
    }

    public void setId(TaskExecRecordPK id) {
        this.id = id;
    }


    public Date getStopDateTime() {
        return stopDateTime;
    }

    public void setStopDateTime(Date stopDateTime) {
        this.stopDateTime = stopDateTime;
    }
}
