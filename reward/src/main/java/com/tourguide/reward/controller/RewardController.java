package com.tourguide.reward.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.reward.controller.dto.AttractionDto;
import com.tourguide.reward.controller.dto.VisitedLocationWithUserNameDto;
import com.tourguide.reward.domain.UserReward;
import com.tourguide.reward.service.RewardsService;

@RestController
public class RewardController {
	
	@Autowired
	private RewardsService rewardService;
	
	@RequestMapping("/calculateRewards")
	public void calculateRewards(@RequestParam UUID userId,@RequestParam List<VisitedLocationWithUserNameDto> userVisitedLocations, @RequestParam List<AttractionDto> attractions) {
		rewardService.calculateRewards(userId, userVisitedLocations, attractions);
	}
	
	@RequestMapping("/getRewards")
	public List<UserReward> getRewards(@RequestParam UUID userId,@RequestParam String userName) {
		return rewardService.getUserRewards(userId);
	}
}
