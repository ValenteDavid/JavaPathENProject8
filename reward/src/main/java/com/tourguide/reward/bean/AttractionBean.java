package com.tourguide.reward.bean;

import java.util.UUID;

import gpsUtil.location.Attraction;

public class AttractionBean {

	private String attractionName;
	private String city;
	private String state;
	private UUID attractionId;

	private double latitude;
	private double longitude;

	public AttractionBean() {
	}

	public AttractionBean(String attractionName, String city, String state, UUID attractionId, double latitude,
			double longitude) {
		this.attractionName = attractionName;
		this.city = city;
		this.state = state;
		this.attractionId = attractionId;
		this.latitude = latitude;
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

	public static AttractionBean convertToDto(Attraction attraction) {
		return new AttractionBean(attraction.attractionName,
				attraction.city, attraction.state, attraction.attractionId, attraction.latitude, attraction.longitude);
	}

	@Override
	public String toString() {
		return "AttractionDto [attractionName=" + attractionName + ", city=" + city + ", state=" + state
				+ ", attractionId=" + attractionId + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
