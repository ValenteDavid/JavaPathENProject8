package com.tourguide.gps.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.gps.beans.VisitedLocationBean;
import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.proxies.TourGuideProxy;
import com.tourguide.gps.proxies.UserProxy;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@Service
public class GpsServiceImpl implements GpsService {
	
	private static final Logger logger = LoggerFactory.getLogger(GpsServiceImpl.class);

	@Autowired
	private TourGuideProxy tourGuideProxy;
	@Autowired
	private UserProxy userProxy;
	
	@Autowired
	private VisitedLocationDao visitedLocationDao;

	@Override
	public Location getUserLocation(String userName) {
		VisitedLocation visitedLocation;
		if (getVisitedLocations(userName).size() > 0) {
			visitedLocation = VisitedLocationBean.convertToModel(tourGuideProxy.getLastVisitedLocation(userName));
		} else {
			visitedLocation = VisitedLocationBean.convertToModel(tourGuideProxy.trackUserLocation(userName));
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
	
}

