package com.tourguide.gps.controller.dto;

import java.util.Date;
import java.util.UUID;

import gpsUtil.location.VisitedLocation;

public class VisitedLocationDto {
	
	private UUID userId;
	private double latitude;
	private double longitude;
	private Date timeVisited;
	
	public VisitedLocationDto() {
	}
	public VisitedLocationDto(UUID userId, double latitude, double longitude, Date timeVisited) {
		this.userId = userId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timeVisited = timeVisited;
	}
	
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Date getTimeVisited() {
		return timeVisited;
	}
	public void setTimeVisited(Date timeVisited) {
		this.timeVisited = timeVisited;
	}
	
	public static VisitedLocationDto convertToDto (VisitedLocation visitedLocation) {
		return new VisitedLocationDto(
				visitedLocation.userId,
				visitedLocation.location.latitude,
				visitedLocation.location.longitude,
				visitedLocation.timeVisited);
	}
	
	@Override
	public String toString() {
		return "VisitedLocationDto [userId=" + userId + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", timeVisited=" + timeVisited + "]";
	}
	
}
