package com.kejikeji.lbs.view.bean;

public class CommentResult extends Result {
	private Long commentor;
	private String username;
	private String comment;
	
	public Long getCommentor() {
		return commentor;
	}
	public void setCommentor(Long commentor) {
		this.commentor = commentor;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
