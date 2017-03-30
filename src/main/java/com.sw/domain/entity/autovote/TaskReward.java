package com.sw.domain.entity.autovote;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TASK_REWARD")
public class TaskReward {
    @Id
    private TaskRewardPK id;
    @Column(name = "QUANTITY")
    private int quantity;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPLOAD_DATE_TIME")
    private Date uploadDateTime;

    public TaskReward() {
    }

    public TaskReward(TaskRewardPK id, int quantity, Date uploadDateTime) {
        this.id = id;
        this.quantity = quantity;
        this.uploadDateTime = uploadDateTime;
    }

    public TaskRewardPK getId() {
        return id;
    }

    public void setId(TaskRewardPK id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(Date uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }
}
