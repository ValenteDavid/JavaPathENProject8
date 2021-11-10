package com.tourguide.reward.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.reward.controller.dto.AttractionDto;
import com.tourguide.reward.controller.dto.VisitedLocationWithUserNameDto;
import com.tourguide.reward.domain.UserReward;
import com.tourguide.reward.service.RewardsService;

/**
 * Reward controller
 * 
 * @author David
 *
 */
@RestController
public class RewardController {

	/**
	 * @see RewardsService
	 */
	@Autowired
	private RewardsService rewardService;

	private ExecutorService executor = Executors.newFixedThreadPool(100);

	/**
	 * Endpoint /calculateRewards
	 * 
	 * @param userId   : user id
	 * @param userName : user name
	 * @see com.tourguide.user.domain.User
	 */
	@RequestMapping("/calculateRewards")
	public void calculateRewards(@RequestParam UUID userId, @RequestParam String userName,
			@RequestParam List<VisitedLocationWithUserNameDto> userVisitedLocations,
			@RequestParam List<AttractionDto> attractions) {
		executor.execute(() -> {
			rewardService.calculateRewards(userId, userName,userVisitedLocations,attractions);
		});
	}

	/**
	 * Endpoint /getRewards
	 * 
	 * @param userName ; user name
	 * @return list user rewards
	 * @see UserReward
	 * @see com.tourguide.user.domain.User
	 */
	@RequestMapping("/getRewards")
	public List<UserReward> getRewards(@RequestParam String userName) {
		return rewardService.getUserRewards(userName);
	}

	/**
	 * Endpoint /getRewardsPoints
	 * 
	 * @param attractionId : attraction id
	 * @param userId       : user id
	 * @return user reward point
	 * @see com.tourguide.user.domain.User
	 */
	@RequestMapping("/getRewardsPoints")
	public int getRewardsPoints(@RequestParam UUID attractionId, @RequestParam UUID userId) {
		return rewardService.getRewardPoints(attractionId, userId);
	}
}
