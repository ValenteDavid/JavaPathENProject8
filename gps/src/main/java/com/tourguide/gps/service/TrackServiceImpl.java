package com.tourguide.gps.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.gps.controller.dto.AttractionDto;
import com.tourguide.gps.controller.dto.RewardDataDto;
import com.tourguide.gps.controller.dto.VisitedLocationWithUserNameDto;
import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.domain.VisitedLocationWithUserName;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.proxies.UserProxy;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;

/**
 * Define methods to tracker
 *
 */

@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private GpsService gpsService;
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
	public VisitedLocationWithUserName trackUserLocation(UUID userId, String userName) {
//		VisitedLocation visitedLocation = gpsUtil.getUserLocation(userProxy.getUserId(userName));
		VisitedLocationWithUserName visitedLocationWithUserName = gpsService.createVistitedLocationWithUserName(userId,
				userName);
		visitedLocationDao.save(visitedLocationWithUserName);

//		RewardDataDto rewardDataDto = new RewardDataDto();
//
//		rewardDataDto.setAttractions(gpsService.getAttractions().stream()
//				.map(attraction -> AttractionDto.convertToDto(attraction))
//				.collect(Collectors.toList()));
//
//		rewardDataDto.setUserVisitedLocations(
//				gpsService.getVisitedLocations(userId).stream()
//						.map(visitedLocationWithUserNameDto -> VisitedLocationWithUserNameDto
//								.convertToDto(visitedLocationWithUserNameDto, userName))
//						.collect(Collectors.toList()));

		rewardProxy.calculateRewards(userId,userName);
		return visitedLocationWithUserName;
	}

}
