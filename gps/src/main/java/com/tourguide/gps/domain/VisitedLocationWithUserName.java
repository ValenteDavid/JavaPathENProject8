package com.tourguide.gps.domain;

import java.util.Date;
import java.util.UUID;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

public class VisitedLocationWithUserName extends VisitedLocation {

	private final UUID id;
	private final String userName;

	public VisitedLocationWithUserName(UUID id, UUID userId,String userName, Location location, Date timeVisited) {
		super(userId, location, timeVisited);
		this.id = id;
		this.userName = userName;
	}

	public UUID getId() {
		return id;
	}

	public UUID getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public Location getLocation() {
		return location;
	}
	
	public double getLocationLatitude() {
		return location.latitude;
	}
	
	public double getLocationLongitude() {
		return location.longitude;
	}

	public Date getTimeVisited() {
		return timeVisited;
	}

	public static VisitedLocation convertToDomain(VisitedLocationWithUserName visitedLocationWithUserName) {
		return new VisitedLocation(
				visitedLocationWithUserName.getUserId(),
				new Location(visitedLocationWithUserName.getLocationLatitude(), visitedLocationWithUserName.getLocationLongitude()),
				visitedLocationWithUserName.getTimeVisited());
	}

	@Override
	public String toString() {
		return "VisitedLocationWithUserName [id=" + id + ", userName=" + userName + ", userId=" + userId + ", location="
				+ location + ", timeVisited=" + timeVisited + "]";
	}

}
