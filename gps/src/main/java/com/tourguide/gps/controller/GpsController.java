package com.tourguide.gps.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.gps.controller.dto.LocationDto;
import com.tourguide.gps.service.GpsService;

import gpsUtil.location.Location;

@RestController
public class GpsController {

	@Autowired
	private GpsService gpsService;

	@RequestMapping("/getLocation")
	public LocationDto getLocation(@RequestParam String userName) {
		LocationDto locationDto;
		locationDto = LocationDto.convertToDto(gpsService.getUserLocation(userName));
		return locationDto;
	}
	
	@RequestMapping("/addVisitedLocation")
	public void addVisitedLocation(@RequestParam UUID uuid,@RequestParam double latitude,@RequestParam double longitude,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timeVisited) {
		gpsService.addVisitedLocation(uuid, new Location(latitude, longitude), timeVisited);
	}
}
