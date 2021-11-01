package com.tourguide.gps.controller.dto;

import java.util.UUID;

import gpsUtil.location.Location;

public class CurrentLocationDto {
	
	private UUID userId;
	private Location location;
	
	public CurrentLocationDto() {
	}

	public CurrentLocationDto(UUID userId, Location location) {
		this.userId = userId;
		this.location = location;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
