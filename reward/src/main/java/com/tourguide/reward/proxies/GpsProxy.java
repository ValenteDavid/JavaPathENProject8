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

/**
 * Gps proxy
 * @author David
 * @see com.tourguide.gps.controller.LocationController
 */
@FeignClient(name = "gps", url = "localhost:9000")
public interface GpsProxy {

	/**
	 * @see com.tourguide.gps.controller.LocationController
	 */
	@RequestMapping("/addVisitedLocation")
	void addVisitedLocation(@RequestParam UUID userId, @RequestParam String userName, @RequestParam double latitude, @RequestParam double longitude,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timeVisited);

	/**
	 * @see com.tourguide.gps.controller.LocationController
	 */
	@RequestMapping("/getAttractions")
	List<AttractionDto> getAttractions();

	/**
	 * @see com.tourguide.gps.controller.LocationController
	 */
	@RequestMapping("/getVisitedLocations")
	List<VisitedLocationWithUserNameDto> getVisitedLocations(@RequestParam String userName);

	/**
	 * @see com.tourguide.gps.controller.LocationController
	 */
	@RequestMapping("/nearAttraction")
	boolean nearAttraction(@RequestParam double location1Latitude, @RequestParam double location1Longitude,
			@RequestParam double location2Latitude, @RequestParam double location2Longitude,
			@RequestParam int nearMaxDistance);

	/**
	 * @see com.tourguide.gps.controller.LocationController
	 */
	@RequestMapping("/deleteAll")
	void deleteAll();
}
