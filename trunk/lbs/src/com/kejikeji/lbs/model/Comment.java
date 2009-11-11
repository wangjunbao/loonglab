package com.kejikeji.lbs.model;

/**
 * @hibernate.class table="i_comment" dynamic-update="true"
 * @author xpdragon
 *
 */
public class Comment {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private Long id;
	private String title;
	private String content;
	private Message message;
	
	private User publisher;

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

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

	
	
	
}
