package com.tourguide.reward.proxies;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

@FeignClient(name = "gps", url = "localhost:9000")
public interface GpsProxy {
	
	@RequestMapping("/addVisitedLocation")
	void addVisitedLocation(@RequestParam UUID uuid,@RequestParam double latitude,@RequestParam double longitude,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timeVisited);

	@RequestMapping("/getAttractions")
	List<Attraction> getAttractions();
	
	@RequestMapping("/getVisitedLocations")
	List<VisitedLocation> getVisitedLocations(@RequestParam String userName);

}
