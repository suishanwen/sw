package com.sw.domain.entity.note;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "note")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String poster;
	@Column(unique = true)
	private String title;
	@Column(length = 2000)
	private String content;
	@Column(name = "post_time")
	private Date postTime;
	@Column(name = "edit_time")
	private Date editTime;
	private String ip;
	private String summary;
	private String tag;
	private Integer recommend;


	public Note() {
	}

	public Note(String poster, String title, String content, Date postTime, Date editTime, String ip) {
		this.poster = poster;
		this.title = title;
		this.content = content;
		this.postTime = postTime;
		this.editTime = editTime;
		this.ip = ip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
}
