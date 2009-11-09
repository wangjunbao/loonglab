package com.kejikeji.lbs.model;

/**
 * @hibernate.class table="l_location_catalog" dynamic-update="true"
 * @author xpdragon
 *
 */
public class LocationCatalog {
	/**
	 * @hibernate.id generator-class="assigned"
	 */
	private String code;
	/**
	 * @hibernate.property
	 */
	private String name;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
