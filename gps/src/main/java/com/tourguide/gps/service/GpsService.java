package com.tourguide.gps.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.tourguide.gps.domain.VisitedLocationWithUserName;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

public interface GpsService {

	Location getUserLocation(String userName);
	
	List<VisitedLocationWithUserName> getVisitedLocations(String userName);

	VisitedLocation getLastVisitedLocation(String userName);
	
	List<Attraction> getAttractions();

	boolean nearAttraction(Location location1, Location location2, int nearMaxDistance);

	VisitedLocation getUserVisitedLocation(String userName);

	double getDistance(Location loc1, Location loc2);

	void addVisitedLocation(UUID userId,String userName, Location location, Date timeVisited);
	
	VisitedLocationWithUserName createVistitedLocationWithUserName(UUID userId,String userName);

	List<Attraction> getUserNearByAttractions(VisitedLocation visitedLocation);

}
