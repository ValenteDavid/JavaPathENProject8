package com.tourguide.gps.controller.dto;

import gpsUtil.location.Location;

public class LocationDto {

	private double longitude;
	private double latitude;
	
	public LocationDto() {
		super();
	}

	public LocationDto(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
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
		return new LocationDto(location.longitude,location.latitude);
	}

	@Override
	public String toString() {
		return "LocationDto [longitude=" + longitude + ", latitude=" + latitude + "]";
	}

}
