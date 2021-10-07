package com.tourguide.reward.controller.dto;

import java.util.UUID;

public class AttractionDto {

	private String attractionName;
	private String city;
	private String state;
	private UUID attractionId;

	private double latitude;
	private double longitude;

	public AttractionDto() {
	}

	public AttractionDto(String attractionName, String city, String state, UUID attractionId, double latitude,
			double longitude) {
		this.attractionName = attractionName;
		this.city = city;
		this.state = state;
		this.attractionId = attractionId;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	
	
	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setAttractionId(UUID attractionId) {
		this.attractionId = attractionId;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getAttractionName() {
		return attractionName;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public UUID getAttractionId() {
		return attractionId;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return "AttractionDto [attractionName=" + attractionName + ", city=" + city + ", state=" + state
				+ ", attractionId=" + attractionId + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
