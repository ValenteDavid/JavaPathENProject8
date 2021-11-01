package com.tourguide.gps.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.tourguide.gps.domain.VisitedLocationWithUserName;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

/**
 * Gps service
 * 
 * @author David
 *
 */
public interface GpsService {

	/**
	 * Get use location
	 * 
	 * @param userName : user name
	 * @return location
	 */
	Location getUserLocation(String userName);

	/**
	 * Get visited location
	 * @param userName
	 * @return visited location list
	 */
	List<VisitedLocationWithUserName> getVisitedLocations(String userName);

	/**
	 * Get last visited location
	 * @param userName :user name
	 * @return last visited location
	 */
	VisitedLocation getLastVisitedLocation(String userName);

	/**
	 * Get attractions
	 * @return attractions list
	 */
	List<Attraction> getAttractions();

	/**
	 * Define if this location is near
	 * @param location1 : location n°1
	 * @param location2 : location n°2
	 * @param nearMaxDistance : max distance between
	 * @return true if distance < nearMaxDistance, else false
	 */
	boolean nearAttraction(Location location1, Location location2, int nearMaxDistance);

	/**
	 * Get user visited location
	 * @param userName : user name
	 * @return visitedLocation
	 */
	VisitedLocation getUserVisitedLocation(String userName);

	/**
	 * Get distance 
	 * @param loc1 : location n°1
	 * @param loc2 : location n°2
	 * @return distance between loc1 and loc2
	 */
	double getDistance(Location loc1, Location loc2);

	/**
	 * Add visited location
	 * @param userId : user id
	 * @param userName : user name
	 * @param location : location
	 * @param timeVisited : time visited
	 */
	void addVisitedLocation(UUID userId, String userName, Location location, Date timeVisited);

	/**
	 * Get near attraction of user
	 * @param visitedLocation : visited location
	 * @return attractions list
	 */
	List<Attraction> getUserNearByAttractions(VisitedLocation visitedLocation);

	/**
	 * Get visited location
	 * @param userId : user id
	 * @return visited location list at this user id
	 */
	List<VisitedLocationWithUserName> getVisitedLocations(UUID userId);

	/**
	 * Define if in range 
	 * @param attraction : attraction
	 * @param location : location
	 * @return true if in range, else false
	 */
	boolean isWithinAttractionProximity(Attraction attraction, Location location);

	/**
	 * Get last location
	 * @param userName : user name
	 * @return last location
	 */
	Location getLastLocation(String userName);

	/**
	 * Delete all visited location
	 */
	void deleteAll();

}
