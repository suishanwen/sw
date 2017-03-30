package com.sw.domain.entity.autovote;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "BEST_TASK")
public class BestTask {
    @Id
    @Column(name = "TASK_SORT")
    private int taskSort;
    @Column(name = "TASK_NAME")
    private String taskName;
    @Column(name = "BACKGROUND_NO")
    private String backgroundNo;
    @Column(name = "ID_CATE")
    private String idCate;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE_TIME")
    private Date updateDateTime;

    public BestTask() {
    }

    public BestTask(int taskSort, String taskName, String backgroundNo, String idCate, BigDecimal price, Date updateDateTime) {
        this.taskSort = taskSort;
        this.taskName = taskName;
        this.backgroundNo = backgroundNo;
        this.idCate = idCate;
        this.price = price;
        this.updateDateTime = updateDateTime;
    }

    public void setBestTask(String taskName, String backgroundNo, String idCate, BigDecimal price, Date updateDateTime) {
        this.taskName = taskName;
        this.backgroundNo = backgroundNo;
        this.idCate = idCate;
        this.price = price;
        this.updateDateTime = updateDateTime;
    }

    public int getTaskSort() {
        return taskSort;
    }

    public void setTaskSort(int taskSort) {
        this.taskSort = taskSort;
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

    public String getIdCate() {
        return idCate;
    }

    public void setIdCate(String idCate) {
        this.idCate = idCate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
