package com.tourguide.gps.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.proxies.UserProxy;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@Service
public class GpsServiceImpl implements GpsService {
	private static final Logger logger = LoggerFactory.getLogger(GpsServiceImpl.class);
	
	private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

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
		VisitedLocation visitedLocation;
		if (getVisitedLocations(userName).size() > 0) {
			visitedLocation = getLastVisitedLocation(userName);
		} else {
			visitedLocation = trackService.trackUserLocation(userName);
		}
		Location location = visitedLocation.location;
		logger.info("Location : latitude {} longitude {}",location.latitude,location.longitude);
		return location;
	}
	
	@Override
	public void addVisitedLocation(UUID id, Location location, Date timeVisited) {
		visitedLocationDao.save(new VisitedLocation(
				id,
				location,
				timeVisited));
	}

	@Override
	public List<VisitedLocation> getVisitedLocations(String userName) {
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
	
}

