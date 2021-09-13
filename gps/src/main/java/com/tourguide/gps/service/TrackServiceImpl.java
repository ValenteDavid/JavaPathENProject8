package com.tourguide.gps.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.proxies.UserProxy;

import gpsUtil.GpsUtil;
import gpsUtil.location.VisitedLocation;

@Service
public class TrackServiceImpl implements TrackService{

	@Autowired
	private UserProxy userProxy;
	@Autowired
	private RewardProxy rewardProxy;
	@Autowired
	private GpsUtil gpsUtil;

	public VisitedLocation trackUserLocation(String userName) {
		UUID id = userProxy.getUserId(userName);
		VisitedLocation visitedLocation = gpsUtil.getUserLocation(id);
		rewardProxy.calculateRewards(userName);
		return visitedLocation;
	}

}
