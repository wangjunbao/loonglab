package com.kejikeji.lbs.model;

public class User {
	private Long id;
	private String name;
	private String passwd;
	private LocationCatalog curLocation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public LocationCatalog getCurLocation() {
		return curLocation;
	}
	public void setCurLocation(LocationCatalog curLocation) {
		this.curLocation = curLocation;
	}

	
	
	
}
