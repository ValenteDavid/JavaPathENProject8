package com.tourguide.gps.service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.gps.controller.dto.AttractionDto;
import com.tourguide.gps.controller.dto.VisitedLocationWithUserNameDto;
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
	private GpsService gpsService;
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
				rewardProxy.calculateRewards(
						userId,
						userName,
						gpsService.getVisitedLocations(userName).stream()
						.map(visitedLocation -> VisitedLocationWithUserNameDto.convertToDto(visitedLocation, userName))
						.collect(Collectors.toList()),
						gpsService.getAttractions().stream()
						.map(attraction -> AttractionDto.convertToDto(attraction))
						.collect(Collectors.toList())
						);
			});
		});
	}

}
