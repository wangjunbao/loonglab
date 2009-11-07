package com.kejikeji.lbs.model;

public class GpsLocation {
	private Long id;
	private double minLong;
	private double minLati;
	private double maxLong;
	private double maxLati;
	
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
