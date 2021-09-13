package com.tourguide.gps.controller.dto;

import gpsUtil.location.Location;

public class LocationDto {

	private double latitude;
	private double longitude;
	
	public LocationDto() {
		super();
	}

	public LocationDto(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public static LocationDto convertToDto (Location location) {
		return new LocationDto(location.latitude,location.longitude);
	}

	@Override
	public String toString() {
		return "LocationDto [longitude=" + longitude + ", latitude=" + latitude + "]";
	}

}
