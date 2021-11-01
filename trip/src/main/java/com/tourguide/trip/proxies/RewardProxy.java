package com.tourguide.trip.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Reward proxy
 * @author David
 * @see com.tourguide.reward.controller
 */
@FeignClient(name = "reward", url = "localhost:9001")
public interface RewardProxy {

	/**
	 * @see com.tourguide.reward.controller
	 */
	@RequestMapping("/getRewardsPoints")
	int getRewardsPoints(@RequestParam String userName);

}
