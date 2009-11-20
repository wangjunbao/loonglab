package com.kejikeji.lbs.model;

public class IpLocation {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private Long id;
	/**
	 * @hibernate.property
	 */
	private String ip;
	/**
	 * @hibernate.many-to-one class="cn.kejikeji.lbs.model.LocationCatalog"
	 */
	private LocationCatalog location;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public LocationCatalog getLocation() {
		return location;
	}
	public void setLocation(LocationCatalog location) {
		this.location = location;
	}
	
	
}
