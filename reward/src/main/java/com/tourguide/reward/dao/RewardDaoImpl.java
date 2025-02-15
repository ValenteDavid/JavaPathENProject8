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
	public List<UserReward> getUserRewards() {
		return userRewards;
	}

	@Override
	public synchronized List<UserReward> findByUserId(UUID userId) {
		return userRewards.stream()
				.filter(userReward -> userReward.getUserId()
						.equals(userId))
				.collect(Collectors.toList());
	}

	@Override
	public synchronized List<UserReward> findByUserName(String userName) {
		return userRewards.stream()
				.filter(userReward -> userReward.getUserName().equals(userName))
				.collect(Collectors.toList());
	}

	@Override
	public synchronized UserReward save(UserReward userReward) {
		userRewards.add(userReward);
		return userReward;
	}

	@Override
	public void deleteAll() {
		userRewards = new ArrayList<>();
	}

}
