package com.tourguide.reward.domain;

import java.util.UUID;

public class UserReward {

	private final UUID userId;
	private final UUID visitedLocationId;
	private final UUID attractionId;
	private int rewardPoints;

	public UserReward(UUID userId, UUID visitedLocationId, UUID attractionId, int rewardPoints) {
		this.userId = userId;
		this.visitedLocationId = visitedLocationId;
		this.attractionId = attractionId;
		this.rewardPoints = rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public UUID getVisitedLocationId() {
		return visitedLocationId;
	}

	public UUID getAttractionId() {
		return attractionId;
	}

	public UUID getUserId() {
		return userId;
	}

}