package com.tourguide.gps.proxies;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "reward", url = "localhost:9001")
public interface RewardProxy {

	void calculateRewards(UUID id);

}
