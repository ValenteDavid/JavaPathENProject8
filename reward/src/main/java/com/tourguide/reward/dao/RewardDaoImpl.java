package com.tourguide.reward.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tourguide.reward.domain.UserReward;

@Repository
public class RewardDaoImpl implements RewardDao {
	
	List<UserReward> userRewards = new ArrayList<>();

	@Override
	public List<UserReward> findByUserId(UUID userId) {
		return userRewards.stream()
		.filter(userReward -> userReward.visitedLocation.userId.equals(userId))
		.collect(Collectors.toList());
	}

	@Override
	public UserReward save(UserReward userReward) {
		userRewards.add(userReward);
		return userReward;
	}

}
