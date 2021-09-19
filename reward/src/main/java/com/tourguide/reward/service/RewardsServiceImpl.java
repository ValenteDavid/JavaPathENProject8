package com.tourguide.reward.service;

import java.util.List;
import java.util.UUID;

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
//	private int attractionProximityRange = 200;
	private int attractionProximityRange = 999999999;

	@Autowired
	private GpsProxy gpsProxy;

	@Autowired
	private RewardDao rewardDao;

	@Autowired
	private RewardCentral rewardsCentral;

	@Override
	public void calculateRewards(UUID userId, List<VisitedLocationWithUserNameDto> userVisitedLocations,
			List<AttractionDto> attractions) {
		for (VisitedLocationWithUserNameDto visitedLocation : userVisitedLocations) {
			for (AttractionDto attraction : attractions) {
				if (getUserRewards(userId).stream()
						.filter(reward -> attractions.stream()
								.filter(attractionInList -> attractionInList.getAttractionId()
										.equals(reward.getAttractionId()))
								.map(attractionInList -> attractionInList.getAttractionName())
								.findFirst().get()

								.equals(attraction.getAttractionName()))
						.count() == 0) {
					if (gpsProxy.nearAttraction(visitedLocation.getLatitude(), visitedLocation.getLongitude(),
							attraction.getLatitude(), attraction.getLongitude(), attractionProximityRange)) {
						addUserRewards(new UserReward(userId, visitedLocation.getId(), attraction.getAttractionId(),
								getRewardPoints(attraction.getAttractionId(), userId)));
					}
				}
			}
		}
	}

	public List<UserReward> getUserRewards(UUID userId) {
		return rewardDao.findByUserId(userId);
	}

	public UserReward addUserRewards(UserReward userReward) {
		return rewardDao.save(userReward);
	}

	private int getRewardPoints(UUID attractionId, UUID userId) {
		return rewardsCentral.getAttractionRewardPoints(attractionId, userId);
	}

}
