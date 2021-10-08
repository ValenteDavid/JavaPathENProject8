package com.tourguide.reward.service;

import java.util.List;
import java.util.UUID;

import com.tourguide.reward.controller.dto.AttractionDto;
import com.tourguide.reward.controller.dto.VisitedLocationWithUserNameDto;
import com.tourguide.reward.domain.UserReward;

public interface RewardsService {
	
	List<UserReward> getUserRewards(UUID userId);

	void calculateRewards(UUID userId, String userName);

	void calculateRewards(UUID userId, String userName, List<VisitedLocationWithUserNameDto> userVisitedLocations,
			List<AttractionDto> attractions);

	List<UserReward> getUserRewards(String userName);

}
