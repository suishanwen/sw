package com.sw.domain.entity.autovote;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TASK_VS_BACKGROUND")
public class TaskVsBackground {
    @Id
    private TaskVsBackgroundPK id;
    @Column(name = "QUANTITY_REQUIRE")
    private BigDecimal quantityRequire;
    @Column(name = "QUANTITY_FINISHED")
    private BigDecimal quantityFinished;
    @Column(name = "ID_ACTIVE")
    private int idActive;
    @Column(name = "ID_TOTAL")
    private int idTotal;
    @Column(name = "ID_ALLOW")
    private int idAllow;
    @Column(name = "ID_CATE")
    private String idCate;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE_TIME")
    private Date createDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE_TIME")
    private Date updateDateTime;

    public TaskVsBackground() {
    }

    public TaskVsBackground(TaskVsBackgroundPK id, BigDecimal quantityRequire, BigDecimal quantityFinished, int idActive, int idTotal, int idAllow, String idCate, BigDecimal price, Date createDateTime, Date updateDateTime) {
        this.id = id;
        this.quantityRequire = quantityRequire;
        this.quantityFinished = quantityFinished;
        this.idActive = idActive;
        this.idTotal = idTotal;
        this.idAllow = idAllow;
        this.idCate = idCate;
        this.price = price;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }

    public void updateBackground(BigDecimal quantityRequire, BigDecimal quantityFinished, int idActive, int idTotal, int idAllow, String idCate, BigDecimal price,  Date updateDateTime) {
        this.quantityRequire = quantityRequire;
        this.quantityFinished = quantityFinished;
        this.idActive = idActive;
        this.idTotal = idTotal;
        this.idAllow = idAllow;
        this.idCate = idCate;
        this.price = price;
        this.updateDateTime = updateDateTime;
    }


    public TaskVsBackgroundPK getId() {
        return id;
    }

    public void setId(TaskVsBackgroundPK id) {
        this.id = id;
    }

    public BigDecimal getQuantityRequire() {
        return quantityRequire;
    }

    public void setQuantityRequire(BigDecimal quantityRequire) {
        this.quantityRequire = quantityRequire;
    }

    public BigDecimal getQuantityFinished() {
        return quantityFinished;
    }

    public void setQuantityFinished(BigDecimal quantityFinished) {
        this.quantityFinished = quantityFinished;
    }

    public int getIdActive() {
        return idActive;
    }

    public void setIdActive(int idActive) {
        this.idActive = idActive;
    }

    public int getIdTotal() {
        return idTotal;
    }

    public void setIdTotal(int idTotal) {
        this.idTotal = idTotal;
    }

    public int getIdAllow() {
        return idAllow;
    }

    public void setIdAllow(int idAllow) {
        this.idAllow = idAllow;
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

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
