package com.tourguide.gps.service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.domain.VisitedLocationWithUserName;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.tracker.Tracker;

import gpsUtil.GpsUtil;

@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private VisitedLocationDao visitedLocationDao;
	@Autowired
	private RewardProxy rewardProxy;
	@Autowired
	private GpsUtil gpsUtil;

	private ExecutorService executor = Executors.newFixedThreadPool(100);
	private Tracker tracker;
	
	public TrackServiceImpl() {
		tracker = new Tracker();
		addShutDownHook();
	}
	
	private void addShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() { 
		      public void run() {
		        tracker.stopTracking();
		      } 
		    }); 
	}

	@Override
	public void trackUserLocation(UUID userId, String userName) {
		executor.execute(() -> {
			VisitedLocationWithUserName visitedLocationWithUserName = VisitedLocationWithUserName
					.domainConvertTo(gpsUtil.getUserLocation(userId), userName);
			visitedLocationDao.save(visitedLocationWithUserName);
			executor.execute(() -> {
				rewardProxy.calculateRewards(userId, userName);
			});
		});
	}

}
