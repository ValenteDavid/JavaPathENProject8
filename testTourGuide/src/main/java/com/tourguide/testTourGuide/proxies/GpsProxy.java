package com.tourguide.testTourGuide.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gps", url = "localhost:9000")
public interface GpsProxy {
	
	@RequestMapping("/getLocation")
	public String getLocation(@RequestParam String userName);
	
	@RequestMapping("/getNearbyAttractions")
	public String getNearbyAttractions(@RequestParam String userName);
	
	@RequestMapping("/getAllCurrentLocations")
	public String getAllCurrentLocations();
}
