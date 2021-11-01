package com.tourguide.reward.domain;

import java.util.UUID;

/**
 * User reward
 * @author David
 *
 */
public class UserReward {

	/**
	 * User id
	 * @see com.tourguide.user.domain.User
	 */
	private final UUID userId;
	/**
	 * User name
	 * @see com.tourguide.user.domain.User
	 */
	private final String userName;
	/**
	 * Visited location id
	 */
	private final UUID visitedLocationId;
	/**
	 * Attraction id
	 */
	private final UUID attractionId;
	/**
	 * Attraction name
	 */
	private final String attractionName;
	/**
	 * Reward points
	 */
	private int rewardPoints;

	public UserReward(UUID userId, String userName, UUID visitedLocationId, UUID attractionId, String attractionName, int rewardPoints) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.visitedLocationId = visitedLocationId;
		this.attractionId = attractionId;
		this.rewardPoints = rewardPoints;
		this.attractionName = attractionName;
	}
	
	public String getUserName() {
		return userName;
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

	public String getAttractionName() {
		return attractionName;
	}

	@Override
	public String toString() {
		return "UserReward [userId=" + userId + ", userName=" + userName + ", visitedLocationId=" + visitedLocationId
				+ ", attractionId=" + attractionId + ", attractionName=" + attractionName + ", rewardPoints="
				+ rewardPoints + "]";
	}
	
}