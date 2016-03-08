package com.sw.domain.util;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@XmlTransient
public class PostVo {
    private Integer id;
    private String title;
    private Date postTime;
    private Date editTime;

    public PostVo(Integer id) {
        this.id = id;
    }

    public PostVo(Integer id, String title, Date postTime, Date editTime) {
        this.id = id;
        this.title = title;
        this.postTime = postTime;
        this.editTime = editTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}
