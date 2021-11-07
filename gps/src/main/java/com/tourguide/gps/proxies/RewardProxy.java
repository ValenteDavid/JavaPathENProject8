package com.tourguide.gps.proxies;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.gps.controller.dto.AttractionDto;
import com.tourguide.gps.controller.dto.VisitedLocationWithUserNameDto;

/**
 * Reward proxy
 * @author David
 * @see com.tourguide.reward.controller.RewardController
 *
 */
@FeignClient(name = "reward", url = "localhost:9001")
public interface RewardProxy {

//	@RequestMapping("/calculateRewards")
//	void calculateRewards(@RequestParam UUID userId,@RequestParam String userName);
	
	@RequestMapping("/getRewardsPoints")
	int getRewardsPoints(@RequestParam UUID attractionId,@RequestParam UUID userId);
	
	@RequestMapping("/calculateRewards")
	public void calculateRewards(@RequestParam UUID userId, @RequestParam String userName,
			@RequestParam List<VisitedLocationWithUserNameDto> userVisitedLocations,
			@RequestParam List<AttractionDto> attractions);
	
}
