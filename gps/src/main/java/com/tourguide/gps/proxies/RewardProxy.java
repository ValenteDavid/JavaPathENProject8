package com.tourguide.gps.proxies;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.gps.controller.dto.AttractionDto;
import com.tourguide.gps.controller.dto.VisitedLocationWithUserNameDto;

@FeignClient(name = "reward", url = "localhost:9001")
public interface RewardProxy {

	@RequestMapping("/calculateRewards")
	void calculateRewards(@RequestParam UUID userId,@RequestParam List<VisitedLocationWithUserNameDto> userVisitedLocations, @RequestParam List<AttractionDto> attractions);

}
