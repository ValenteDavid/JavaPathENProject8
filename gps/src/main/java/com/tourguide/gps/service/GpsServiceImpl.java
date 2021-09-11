package com.tourguide.gps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.gps.beans.VisitedLocationBean;
import com.tourguide.gps.proxies.TourGuideProxy;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@Service
public class GpsServiceImpl implements GpsService {
	
	private static final Logger logger = LoggerFactory.getLogger(GpsServiceImpl.class);


	@Autowired
	private TourGuideProxy tourGuideProxy;

	@Override
	public Location getUserLocation(String userName) {
		VisitedLocation visitedLocation;
		if (tourGuideProxy.getVisitedLocations(userName).size() > 0) {
			visitedLocation = VisitedLocationBean.convertToModel(tourGuideProxy.getLastVisitedLocation(userName));
		} else {
			visitedLocation = VisitedLocationBean.convertToModel(tourGuideProxy.trackUserLocation(userName));
		}
		Location location = visitedLocation.location;
		logger.info("Location : latitude {} longitude {}",location.latitude,location.longitude);
		return location;
	}

}
