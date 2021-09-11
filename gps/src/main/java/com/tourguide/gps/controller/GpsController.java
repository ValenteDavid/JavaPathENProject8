package com.tourguide.gps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.gps.controller.dto.LocationDto;
import com.tourguide.gps.service.GpsService;

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
}
