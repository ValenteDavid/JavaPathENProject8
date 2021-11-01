package com.tourguide.gps.proxies;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Reward proxy
 * @author David
 * @see com.tourguide.reward.controller.RewardController
 *
 */
@FeignClient(name = "reward", url = "localhost:9001")
public interface RewardProxy {

	@RequestMapping("/calculateRewards")
	void calculateRewards(@RequestParam UUID userId,@RequestParam String userName);
	
	@RequestMapping("/getRewardsPoints")
	int getRewardsPoints(@RequestParam UUID attractionId,@RequestParam UUID userId);
	
}
