package com.tourguide.reward.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.reward.controller.dto.AttractionDto;
import com.tourguide.reward.controller.dto.VisitedLocationWithUserNameDto;
import com.tourguide.reward.dao.RewardDao;
import com.tourguide.reward.domain.UserReward;
import com.tourguide.reward.proxies.GpsProxy;

import rewardCentral.RewardCentral;

@Service
public class RewardsServiceImpl implements RewardsService {

	// proximity in miles
	private int defaultProximityBuffer = 10;
	private int proximityBuffer = defaultProximityBuffer;
	private int attractionProximityRange = 200;

	@Override
	public void setAttractionProximityRange(int attractionProximityRange) {
		this.attractionProximityRange = attractionProximityRange;
	}

	@Autowired
	private GpsProxy gpsProxy;

	@Autowired
	private RewardDao rewardDao;

	@Autowired
	private RewardCentral rewardsCentral;

	private ExecutorService executor = Executors.newFixedThreadPool(200);

	@Override
	public void calculateRewards(UUID userId, String userName) {
		List<VisitedLocationWithUserNameDto> userVisitedLocations = gpsProxy.getVisitedLocations(userName);
		List<AttractionDto> attractions = gpsProxy.getAttractions();
			calculateRewards(userId, userName, userVisitedLocations, attractions);
	}

	@Override
	public void calculateRewards(UUID userId, String userName,
			List<VisitedLocationWithUserNameDto> userVisitedLocations,
			List<AttractionDto> attractions) {
		executor.execute(() -> {
			List<String> attractionNameInRewardsList = getUserRewards(userId).stream()
					.map(reward -> reward.getAttractionName())
					.collect(Collectors.toList());

			List<AttractionDto> attractionsList = attractions.stream()
					.filter(attraction -> !attractionNameInRewardsList.contains(attraction.getAttractionName()))
					.collect(Collectors.toList());

			for (VisitedLocationWithUserNameDto visitedLocation : userVisitedLocations) {
				for (AttractionDto attraction : attractionsList) {
					if (gpsProxy.nearAttraction(visitedLocation.getLatitude(), visitedLocation.getLongitude(),
							attraction.getLatitude(), attraction.getLongitude(), attractionProximityRange)) {
						executor.execute(() -> {
							addUserRewards(new UserReward(userId, userName, visitedLocation.getId(),
									attraction.getAttractionId(), attraction.getAttractionName(),
									getRewardPoints(attraction.getAttractionId(), userId)));
						});

					}
				}
			}
		});
	}

	@Override
	public List<UserReward> getUserRewards(UUID userId) {
		return rewardDao.findByUserId(userId);
	}

	@Override
	public List<UserReward> getUserRewards(String userName) {
		return rewardDao.findByUserName(userName);
	}

	@Override
	public UserReward addUserRewards(UserReward userReward) {
		return rewardDao.save(userReward);
	}

	@Override
	public int getRewardPoints(UUID attractionId, UUID userId) {
		return rewardsCentral.getAttractionRewardPoints(attractionId, userId);
	}

	@Override
	public void deleteAll() {
		rewardDao.deleteAll();
	}

}
