package com.tourguide.gps.proxies;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.gps.controller.dto.RewardDataDto;

@FeignClient(name = "reward", url = "localhost:9001")
public interface RewardProxy {

	@RequestMapping("/calculateRewardsPrefill")
	void calculateRewards(@RequestParam UUID userId,@RequestParam String userName,@RequestBody RewardDataDto rewardDataDto);
	
	@RequestMapping("/calculateRewards")
	void calculateRewards(@RequestParam UUID userId,@RequestParam String userName);
	
	@RequestMapping("/getRewardsPoints")
	int getRewardsPoints(@RequestParam UUID attractionId,@RequestParam UUID userId);
	
}
