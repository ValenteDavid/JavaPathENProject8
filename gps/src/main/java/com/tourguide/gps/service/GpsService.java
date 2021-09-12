package com.tourguide.gps.service;

import java.util.List;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

public interface GpsService {

	Location getUserLocation(String userName);
	
	List<VisitedLocation> getVisitedLocations(String userName);

}
