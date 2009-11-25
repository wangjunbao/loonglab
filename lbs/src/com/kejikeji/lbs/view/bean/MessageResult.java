package com.kejikeji.lbs.view.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

public class MessageResult extends Result {
	
	static Log log = LogFactory.getLog(MessageResult.class);
	
	private String title;
	private String content;
	private Long postID;
	private Long reporter;
	private String locationCode;
	private String imagelink;
	private String soundlink;
	private String videolink;
	private String createTime;
	private String brief;
	
	List<CommentResult> comments=new ArrayList<CommentResult>();
	
	
	
	public List<CommentResult> getComments() {
		return comments;
	}
	public void setComments(List<CommentResult> comments) {
		this.comments = comments;
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
	public Long getPostID() {
		return postID;
	}
	public void setPostID(Long postID) {
		this.postID = postID;
	}
	public Long getReporter() {
		return reporter;
	}
	public void setReporter(Long reporter) {
		this.reporter = reporter;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getImagelink() {
		return imagelink;
	}
	public void setImagelink(String imagelink) {
		this.imagelink = imagelink;
	}
	public String getSoundlink() {
		return soundlink;
	}
	public void setSoundlink(String soundlink) {
		this.soundlink = soundlink;
	}
	public String getVideolink() {
		return videolink;
	}
	public void setVideolink(String videolink) {
		this.videolink = videolink;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	public void addCommentResult(CommentResult cr){
		comments.add(cr);
	}
	
	
	public static void main(String[] args) {
		MessageResult mr=new MessageResult();
		mr.setResultCode(11);
		mr.setTitle("hahahah");
		mr.setBrief("ha");
		mr.setContent("dddd");
		
		CommentResult cr=new CommentResult();
		cr.setComment("cc");
		cr.setCommentor(123L);
		cr.setUsername("uu");
		mr.addCommentResult(cr);
		
		JSONObject json=new JSONObject(mr,new String[]{"title"});
		
		log.info(json.toString());
	}
	
}
