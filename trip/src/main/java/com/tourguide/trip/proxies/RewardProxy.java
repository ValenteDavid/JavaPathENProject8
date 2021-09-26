package com.tourguide.trip.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "reward", url = "localhost:9001")
public interface RewardProxy {

	@RequestMapping("/getRewardsPoints")
	int getRewardsPoints(@RequestParam String userName);

}
