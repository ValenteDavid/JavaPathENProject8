package com.tourguide.user.proxies;

import java.util.Date;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Gps proxy
 * @author David
 * @see com.tourguide.gps.controller.LocationController
 *
 */
@FeignClient(name = "gps", url = "localhost:9000")
public interface GpsProxy {
	
	/**
	 * @see com.tourguide.gps.controller.LocationController
	 */
	@RequestMapping("/addVisitedLocation")
	void addVisitedLocation(@RequestParam UUID uuid,@RequestParam String userName, @RequestParam double latitude,
			@RequestParam double longitude,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timeVisited);
	
	

}
