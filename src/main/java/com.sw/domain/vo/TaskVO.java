package com.sw.domain.vo;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.Date;

@XmlTransient
public class TaskVO {
    private String taskName;
    private int taskCate;
    private int ipType;
    private String downloadAddress;
    private int isValid;
    private String backgroundNo;
    private BigDecimal quantityRequire;
    private BigDecimal quantityFinished;
    private int idActive;
    private int idTotal;
    private int idAllow;
    private String idCate;
    private BigDecimal price;
    private Date createDateTime;
    private Date updateDateTime;

    public TaskVO() {
    }

    public TaskVO(String TASK_NAME , int taskCate, int ipType, String downloadAddress, int isValid, String backgroundNo, BigDecimal quantityRequire, BigDecimal quantityFinished, int idActive, int idTotal, int idAllow, String idCate, BigDecimal price, Date createDateTime, Date updateDateTime) {
        this.taskName = TASK_NAME ;
        this.taskCate = taskCate;
        this.ipType = ipType;
        this.downloadAddress = downloadAddress;
        this.isValid = isValid;
        this.backgroundNo = backgroundNo;
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

    public String getBackgroundNo() {
        return backgroundNo;
    }

    public void setBackgroundNo(String backgroundNo) {
        this.backgroundNo = backgroundNo;
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
