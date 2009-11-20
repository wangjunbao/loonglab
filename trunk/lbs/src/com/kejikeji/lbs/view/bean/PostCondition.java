package com.kejikeji.lbs.view.bean;

import java.util.Date;

/**
 * 对发布消息的查询参数
 * @author liugang
 *
 */
public class PostCondition {
	private Date expectedDate;
	private Long startPostId;
	private int expectedPostsNum=20;
	private String locationCode;
	private Long userId;
	private String deviceType;
	private String deviceResolution;
	public Date getExpectedDate() {
		return expectedDate;
	}
	
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}
	public Long getStartPostId() {
		return startPostId;
	}
	public void setStartPostId(Long startPostId) {
		this.startPostId = startPostId;
	}
	public int getExpectedPostsNum() {
		return expectedPostsNum;
	}
	public void setExpectedPostsNum(int expectedPostsNum) {
		this.expectedPostsNum = expectedPostsNum;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceResolution() {
		return deviceResolution;
	}
	public void setDeviceResolution(String deviceResolution) {
		this.deviceResolution = deviceResolution;
	}
	
	
}
