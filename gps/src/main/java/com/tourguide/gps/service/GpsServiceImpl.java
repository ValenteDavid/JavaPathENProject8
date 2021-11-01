package com.tourguide.gps.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.domain.VisitedLocationWithUserName;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@Service
public class GpsServiceImpl implements GpsService {

	private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
	private int attractionProximityRange = 200;

	@Autowired
	private GpsUtil gpsUtil;

	@Autowired
	private VisitedLocationDao visitedLocationDao;
	
	@Override
	public Location getUserLocation(String userName) {
		return getUserVisitedLocation(userName).location;
	}

	@Override
	public VisitedLocation getUserVisitedLocation(String userName) {
		return getLastVisitedLocation(userName);
	}

	@Override
	public void addVisitedLocation(UUID userId, String userName, Location location, Date timeVisited) {
		visitedLocationDao.save(new VisitedLocationWithUserName(
				UUID.randomUUID(),
				userId,
				userName, location, timeVisited));
	}

	@Override
	public List<VisitedLocationWithUserName> getVisitedLocations(String userName) {
		return visitedLocationDao.findByUserName(userName);
	}

	@Override
	public List<VisitedLocationWithUserName> getVisitedLocations(UUID userId) {
		return visitedLocationDao.findByUUID(userId);
	}

	@Override
	public VisitedLocation getLastVisitedLocation(String userName) {
		return visitedLocationDao.findByUserNameOrderByTimeVisitedDesc(userName);
	}
	
	@Override
	public Location getLastLocation(String userName) {
		VisitedLocation lastVisitedLocation = visitedLocationDao.findByUserNameOrderByTimeVisitedDesc(userName);
		if(lastVisitedLocation == null) {
			return null;
		}
		else {
			return lastVisitedLocation.location;
		}
	}

	@Override
	public List<Attraction> getAttractions() {
		return gpsUtil.getAttractions();
	}

	@Override
	public boolean nearAttraction(@RequestParam Location location1, @RequestParam Location location2,
			int nearMaxDistance) {
		return getDistance(location1, location2) > nearMaxDistance ? false : true;
	}

	@Override
	public List<Attraction> getUserNearByAttractions(VisitedLocation visitedLocation) {
		List<Attraction> nearbyAttractionsList = gpsUtil.getAttractions();
		nearbyAttractionsList
				.sort((a1, a2) -> Double.compare(getDistance(a1, visitedLocation.location),
						getDistance(a2, visitedLocation.location)));
		return nearbyAttractionsList.subList(0, 5);
	}

	@Override
	public boolean isWithinAttractionProximity(Attraction attraction, Location location) {
		return getDistance(attraction, location) > attractionProximityRange ? false : true;
	}

	@Override
	public double getDistance(Location loc1, Location loc2) {
		double lat1 = Math.toRadians(loc1.latitude);
		double lon1 = Math.toRadians(loc1.longitude);
		double lat2 = Math.toRadians(loc2.latitude);
		double lon2 = Math.toRadians(loc2.longitude);

		double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

		double nauticalMiles = 60 * Math.toDegrees(angle);
		double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
		return statuteMiles;
	}

	@Override
	public void deleteAll() {
		visitedLocationDao.deleteAll();
	}

}
