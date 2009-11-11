package com.kejikeji.lbs.model;

/**
 * @hibernate.class table="i_favorite" dynamic-update="true"
 * @author xpdragon
 *
 */
public class Favorite {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private Long id;
	private User user;
	private LocationCatalog location;
	private String locationName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	
}
