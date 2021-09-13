package com.tourguide.reward.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.reward.service.RewardService;

@RestController
public class RewardController {
	
	@Autowired
	private RewardService rewardService;
	
	@RequestMapping("/calculateRewards")
	public void calculateRewards(@RequestParam String userName) {
		rewardService.calculateRewards(userName);
	}
}
