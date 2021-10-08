package com.tourguide.reward.dao;

import java.util.List;
import java.util.UUID;

import com.tourguide.reward.domain.UserReward;

public interface RewardDao {

	List<UserReward> findByUserId(UUID userId);

	UserReward save(UserReward userReward);

	List<UserReward> findByUserName(String userName);

}
