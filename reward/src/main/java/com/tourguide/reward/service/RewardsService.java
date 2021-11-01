package com.tourguide.reward.service;

import java.util.List;
import java.util.UUID;

import com.tourguide.reward.controller.dto.AttractionDto;
import com.tourguide.reward.controller.dto.VisitedLocationWithUserNameDto;
import com.tourguide.reward.domain.UserReward;

/**
 * Reward service
 * 
 * @author David
 *
 */
public interface RewardsService {

	/**
	 * Get list user rewards
	 * @param userId : user id
	 * @return list user rewards
	 * @see UserReward
	 * @see com.tourguide.user.domain.User
	 */
	List<UserReward> getUserRewards(UUID userId);

	/**
	 * Calculate rewards
	 * @param userId : user id
	 * @param userName : user name
	 * @see com.tourguide.user.domain.User
	 */
	void calculateRewards(UUID userId, String userName);

	/**
	 * Get user rewards
	 * @param userName : user name
	 * @return user rewards list
	 * @see com.tourguide.user.domain.User
	 */
	List<UserReward> getUserRewards(String userName);

	/**
	 * Get reward points for this attraction id
	 * @param attractionId : attraction id
	 * @param userId : user id
	 * @return reward points for this attraction id
	 * @see com.tourguide.user.domain.User
	 */
	int getRewardPoints(UUID attractionId, UUID userId);

	/**
	 * Calculate rewards
	 * @param userId : user id
	 * @param userName : user name
	 * @param userVisitedLocations 
	 * @param attractions
	 * @see VisitedLocationWithUserNameDto
	 * @see AttractionDto
	 * @see com.tourguide.user.domain.User
	 */
	void calculateRewards(UUID userId, String userName, List<VisitedLocationWithUserNameDto> userVisitedLocations,
			List<AttractionDto> attractions);

	/**
	 * Add user reward
	 * @param userReward : user reward
	 * @return user rewards save
	 * @see UserReward
	 * @see com.tourguide.user.domain.User
	 */
	UserReward addUserRewards(UserReward userReward);

	/**
	 * For test only <br>
	 * Delete all reward data 
	 */
	void deleteAll();

	/**
	 * Set attraction proximity range
	 * @param attractionProximityRange : attraction proximity range max
	 */
	void setAttractionProximityRange(int attractionProximityRange);

}
