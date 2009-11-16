package com.kejikeji.lbs.model;

import java.util.Date;

/**
 * @hibernate.class table="i_message" dynamic-update="true"
 * @author xpdragon
 *
 */
public class Message {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private Long id;
	/**
	 * @hibernate.property
	 */
	private String title;
	/**
	 * @hibernate.property
	 */
	private String content;
	/**
	 * @hibernate.many-to-one class="com.kejikeji.lbs.model.User"
	 */
	private User user;
	/**
	 * @hibernate.many-to-one class="com.kejikeji.lbs.model.LocationCatalog"
	 */
	private LocationCatalog location;
	/**
	 * @hibernate.property
	 */
	private String picFile;
	/**
	 * @hibernate.property
	 */
	private String audioFile;
	/**
	 * @hibernate.property
	 */
	private Date pubDate;
	
	
	
	
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocationCatalog getLocation() {
		return location;
	}
	public void setLocation(LocationCatalog location) {
		this.location = location;
	}
	public String getPicFile() {
		return picFile;
	}
	public void setPicFile(String picFile) {
		this.picFile = picFile;
	}
	public String getAudioFile() {
		return audioFile;
	}
	public void setAudioFile(String audioFile) {
		this.audioFile = audioFile;
	}
	
	
}
