package com.tourguide.gps.controller.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import gpsUtil.location.VisitedLocation;

public class VisitedLocationWithUserNameDto {

	private UUID id;
	private String userName;

	private UUID userId;
	private double latitude;
	private double longitude;
	private Date timeVisited;

	public VisitedLocationWithUserNameDto() {
	}

	public VisitedLocationWithUserNameDto(UUID id, String userName, UUID userId, double latitude, double longitude,
			Date timeVisited) {
		this.id = id;
		this.userName = userName;
		this.userId = userId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timeVisited = timeVisited;
	}

	public UUID getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public UUID getUserId() {
		return userId;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public Date getTimeVisited() {
		return timeVisited;
	}
	
	public static VisitedLocationWithUserNameDto convertToDto(VisitedLocation visitedLocations,String userName) {
		return new VisitedLocationWithUserNameDto(
				UUID.randomUUID(),
				userName,
				visitedLocations.userId,
				visitedLocations.location.latitude,
				visitedLocations.location.longitude, 
				visitedLocations.timeVisited);
	}

	@Override
	public String toString() {
		return "VisitedLocationDto [userId=" + userId + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", timeVisited=" + timeVisited + "]";
	}

}
