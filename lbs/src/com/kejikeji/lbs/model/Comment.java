package com.kejikeji.lbs.model;

import java.util.Date;

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
	/**
	 * @hibernate.property
	 */
	private String title;
	/**
	 * @hibernate.property
	 */
	private String comment;

	/**
	 * @hibernate.property
	 */
	private Long postId;
	
	/**
	 * @hibernate.many-to-one class="com.kejikeji.lbs.model.User"
	 */
	private User publisher;
	
	/**
	 * @hibernate.property
	 */
	private Date pubdate;
	
	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String content) {
		this.comment = content;
	}



	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	
	
	
}
