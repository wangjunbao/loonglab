package com.kejikeji.lbs.model;

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
	private String title;
	private String content;
	private User user;
	private LocationCatalog location;
	private String picFile;
	private String audioFile;
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
