package com.tourguide.gps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.proxies.UserProxy;

import gpsUtil.GpsUtil;
import gpsUtil.location.VisitedLocation;

/**
 * Define methods to tracker
 *
 */

@Service
public class TrackServiceImpl implements TrackService{

	@Autowired
	private VisitedLocationDao visitedLocationDao;
	@Autowired
	private UserProxy userProxy;
	@Autowired
	private RewardProxy rewardProxy;
	@Autowired
	private GpsUtil gpsUtil;

	/**
	 * Used to get the visited location of user
	 * 
	 * @param userName The name of user
	 * @return VisitedLocation The visited location of user
	 */
	public VisitedLocation trackUserLocation(String userName) {
		VisitedLocation visitedLocation = gpsUtil.getUserLocation(userProxy.getUserId(userName));
		visitedLocationDao.save(visitedLocation);
		rewardProxy.calculateRewards(userName);
		return visitedLocation;
	}

}
