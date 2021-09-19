package com.tourguide.gps.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.domain.VisitedLocationWithUserName;
import com.tourguide.gps.proxies.UserProxy;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@Service
public class GpsServiceImpl implements GpsService {
	private static final Logger logger = LoggerFactory.getLogger(GpsServiceImpl.class);
	
	private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
	private int attractionProximityRange = 99999999;

	@Autowired
	private UserProxy userProxy;
	@Autowired
	private TrackService trackService;
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
		VisitedLocation visitedLocation;
		if (getVisitedLocations(userName).size() > 0) {
			visitedLocation = getLastVisitedLocation(userName);
		} else {
			visitedLocation = trackService.trackUserLocation(userProxy.getUserId(userName),userName);
		}
		return visitedLocation;
	}
	
	@Override
	public void addVisitedLocation(UUID userId,String userName, Location location, Date timeVisited) {
		visitedLocationDao.save(new VisitedLocationWithUserName(
				UUID.randomUUID(),
				userId,
				userName
				,location
				,timeVisited));
	}

	@Override
	public List<VisitedLocationWithUserName> getVisitedLocations(String userName) {
		return visitedLocationDao.findByUUID(userProxy.getUserId(userName));
	}
	
	@Override
	public VisitedLocation getLastVisitedLocation(String userName) {
		return visitedLocationDao.findByUUIDOrderByTimeVisitedDesc(userProxy.getUserId(userName));
	}

	@Override
	public List<Attraction> getAttractions() {
		return gpsUtil.getAttractions();
	}
	
	@Override
	public boolean nearAttraction(@RequestParam Location location1,@RequestParam Location location2,int nearMaxDistance) {
		return getDistance(location1, location2) > nearMaxDistance ? false : true;
	}
	
	@Override
	public List<Attraction> getUserNearByAttractions(String userName) {
		List<Attraction> nearbyAttractionsList = new ArrayList<>();
		Location userLocation = getUserLocation(userName);
		for(Attraction attraction : gpsUtil.getAttractions()) {
			if(isWithinAttractionProximity(attraction, userLocation)) {
				nearbyAttractionsList.add(attraction);
			}
		}
		nearbyAttractionsList.sort((a1, a2) -> Double.compare(getDistance(a1,userLocation), getDistance(a2,userLocation)));
		return nearbyAttractionsList;
	}
	
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
	
	public VisitedLocationWithUserName createVistitedLocationWithUserName(UUID userId,String userName) {
		return new VisitedLocationWithUserName(UUID.randomUUID(),userId,userName, new Location(generateRandomLatitude(), generateRandomLongitude()),
				new Date());
	}
	
	private double generateRandomLongitude() {
		double leftLimit = -180;
		double rightLimit = 180;
		return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
	}

	private double generateRandomLatitude() {
		double leftLimit = -85.05112878;
		double rightLimit = 85.05112878;
		return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
	}

	
	
}

