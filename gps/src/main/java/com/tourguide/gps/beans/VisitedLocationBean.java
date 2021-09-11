package com.tourguide.gps.beans;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

public class VisitedLocationBean {
	
	private static final Logger log = LoggerFactory.getLogger(VisitedLocationBean.class);


	private final UUID userId;
	private final double longitude;
	private final double latitude;
	private final Date timeVisited;

	public VisitedLocationBean(UUID userId, double longitude, double latitude, Date timeVisited) {
		super();
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.timeVisited = timeVisited;
	}

	public UUID getUserId() {
		return userId;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public Date getTimeVisited() {
		return timeVisited;
	}

	public static VisitedLocation convertToModel(VisitedLocationBean visitedLocationBean) {
		log.info(visitedLocationBean.toString());
		return new VisitedLocation(
				visitedLocationBean.getUserId(),new Location(
						visitedLocationBean.getLatitude(), 
						visitedLocationBean.getLongitude()),
				visitedLocationBean.getTimeVisited());
	}

	@Override
	public String toString() {
		return "VisitedLocationBean [userId=" + userId + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", timeVisited=" + timeVisited + "]";
	}
	
}
