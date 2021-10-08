package com.tourguide.reward.proxies;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.reward.controller.dto.AttractionDto;
import com.tourguide.reward.controller.dto.VisitedLocationWithUserNameDto;

@FeignClient(name = "gps", url = "localhost:9000")
public interface GpsProxy {
	
	@RequestMapping("/addVisitedLocation")
	void addVisitedLocation(@RequestParam UUID uuid,@RequestParam double latitude,@RequestParam double longitude,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timeVisited);

	@RequestMapping("/getAttractions")
	List<AttractionDto> getAttractions();
	
	@RequestMapping("/getVisitedLocations")
	List<VisitedLocationWithUserNameDto> getVisitedLocations(@RequestParam String userName);
	

	@RequestMapping("/nearAttraction")
	boolean nearAttraction(@RequestParam double location1Latitude, @RequestParam double location1Longitude,
			@RequestParam double location2Latitude, @RequestParam double location2Longitude, @RequestParam int nearMaxDistance);

}
