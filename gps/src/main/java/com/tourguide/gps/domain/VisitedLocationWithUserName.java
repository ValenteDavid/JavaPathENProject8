package com.tourguide.gps.domain;

import java.util.Date;
import java.util.UUID;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

/**
 * VisitedLocationWithUserName <br>
 * Extends VisitedLocation to add id & userName
 * @see VisitedLocation
 * @author David
 **/
public class VisitedLocationWithUserName extends VisitedLocation {

	/**
	 * id VisitedLocationWithUserName
	 */
	private final UUID id;
	/**
	 * userName
	 * @see com.tourguide.user.domain.User
	 */
	private final String userName;

	/**
	 * Contructor
	 * @param id
	 * @param userId
	 * @param userName
	 * @param location
	 * @param timeVisited
	 */
	public VisitedLocationWithUserName(UUID id, UUID userId,String userName, Location location, Date timeVisited) {
		super(userId, location, timeVisited);
		this.id = id;
		this.userName = userName;
	}

	/**
	 * Get id
	 * @return id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Get User Id
	 * @return UserId
	 * @see com.tourguide.user.domain.User
	 */
	public UUID getUserId() {
		return userId;
	}

	/**
	 * Get user name
	 * @return UserName
	 * @see com.tourguide.user.domain.User
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Get Location
	 * @return Location
	 * @see VisitedLocation
	 * @see Location
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Get Location Latitude
	 * @return LocationLatitude
	 * @see VisitedLocation
	 * @see Location
	 */
	public double getLocationLatitude() {
		return location.latitude;
	}
	
	/**
	 * Get Location Longitude
	 * @return LocationLongitude
	 * @see VisitedLocation
	 * @see Location
	 */
	public double getLocationLongitude() {
		return location.longitude;
	}

	/**
	 * Get TimeVisited
	 * @return TimeVisited
	 * @see VisitedLocation
	 */
	public Date getTimeVisited() {
		return timeVisited;
	}
	
	/**
	 * Convert VisitedLocation to VisitedLocationWithUserName
	 * @param visitedLocation
	 * @param userName
	 * @return VisitedLocationWithUserName
	 * @see VisitedLocation
	 */
	public static VisitedLocationWithUserName domainConvertTo(VisitedLocation visitedLocation,String userName) {
		return new VisitedLocationWithUserName(
				null,
				visitedLocation.userId,
				userName,
				visitedLocation.location,
				visitedLocation.timeVisited);
	}

	/**
	 * Convert VisitedLocationWithUserName to VisitedLocation
	 * @param visitedLocationWithUserName
	 * @return VisitedLocation
	 * @see VisitedLocation
	 */
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
