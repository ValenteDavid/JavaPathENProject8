package com.tourguide.gps.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

public interface GpsService {

	Location getUserLocation(String userName);
	
	List<VisitedLocation> getVisitedLocations(String userName);

	void addVisitedLocation(UUID id, Location location, Date timeVisited);

	VisitedLocation getLastVisitedLocation(String userName);
	
	List<Attraction> getAttractions();

	boolean nearAttraction(Location location1, Location location2, int nearMaxDistance);

}
