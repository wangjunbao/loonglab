package com.kejikeji.lbs.model;

/**
 * @hibernate.class table="l_gps_location" dynamic-update="true"
 * @author xpdragon
 *
 */
public class GpsLocation {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private Long id;
	/**
	 * @hibernate.property
	 */
	private double minLong;
	/**
	 * @hibernate.property
	 */
	private double minLati;
	/**
	 * @hibernate.property
	 */
	private double maxLong;
	/**
	 * @hibernate.property
	 */
	private double maxLati;
	
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

	public double getMinLong() {
		return minLong;
	}

	public void setMinLong(double minLong) {
		this.minLong = minLong;
	}

	public double getMinLati() {
		return minLati;
	}

	public void setMinLati(double minLati) {
		this.minLati = minLati;
	}

	public double getMaxLong() {
		return maxLong;
	}

	public void setMaxLong(double maxLong) {
		this.maxLong = maxLong;
	}

	public double getMaxLati() {
		return maxLati;
	}

	public void setMaxLati(double maxLati) {
		this.maxLati = maxLati;
	}

	public LocationCatalog getLocation() {
		return location;
	}

	public void setLocation(LocationCatalog location) {
		this.location = location;
	}
	
	
}
