package com.tourguide.reward.dao;

import java.util.List;
import java.util.UUID;

import com.tourguide.reward.domain.UserReward;

/**
 * Reward dao
 * @author David
 *
 */
public interface RewardDao {

	/**
	 * Find by user id
	 * @param userId : user id
	 * @return list user reward at this user name
	 * @see UserReward
	 */
	List<UserReward> findByUserId(UUID userId);

	/**
	 * User save
	 * 
	 * @param userReward : user reward
	 * @return userReward save
	 * @see UserReward
	 */
	UserReward save(UserReward userReward);

	/**
	 * Find by user name
	 * 
	 * @param userName : user name
	 * @return list user reward at this user name
	 * @see UserReward
	 */
	List<UserReward> findByUserName(String userName);

	/**
	 * Delete all reward
	 */
	void deleteAll();

	/**
	 * Get user rewards
	 * 
	 * @return list user reward
	 * @see UserReward
	 */
	List<UserReward> getUserRewards();

}
