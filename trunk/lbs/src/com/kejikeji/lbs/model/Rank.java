package com.kejikeji.lbs.model;

public class Rank {
	private Long id;
	private int rating;
	private User user;
	private Message message;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getValue() {
		return rating;
	}
	public void setValue(int value) {
		this.rating = value;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	
}
