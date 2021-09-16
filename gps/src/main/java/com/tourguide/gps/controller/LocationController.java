package com.tourguide.gps.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.gps.controller.dto.AttractionDto;
import com.tourguide.gps.controller.dto.LocationDto;
import com.tourguide.gps.controller.dto.VisitedLocationDto;
import com.tourguide.gps.service.GpsService;

import gpsUtil.location.Location;

@RestController
public class LocationController {

	@Autowired
	private GpsService gpsService;

	@RequestMapping("/getLocation")
	public LocationDto getLocation(@RequestParam String userName) {
		LocationDto locationDto;
		locationDto = LocationDto.convertToDto(gpsService.getUserLocation(userName));
		return locationDto;
	}

	@RequestMapping("/addVisitedLocation")
	public void addVisitedLocation(@RequestParam UUID uuid, @RequestParam double latitude,
			@RequestParam double longitude,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timeVisited) {
		gpsService.addVisitedLocation(uuid, new Location(latitude, longitude), timeVisited);
	}

	@RequestMapping("/getVisitedLocations")
	public List<VisitedLocationDto> getVisitedLocations(@RequestParam String userName) {
		List<VisitedLocationDto> visitedLocationDtoList = gpsService.getVisitedLocations(userName).stream()
				.map(visitedLocation -> VisitedLocationDto.convertToDto(visitedLocation))
				.collect(Collectors.toList());
		return visitedLocationDtoList;
	}

	public List<AttractionDto> getAttractions() {
		List<AttractionDto> AttractionDTOList = gpsService.getAttractions().stream()
				.map(attraction -> AttractionDto.convertToDto(attraction))
				.collect(Collectors.toList());

		return AttractionDTOList;
	}
	
	@RequestMapping("/nearAttraction")
	public boolean nearAttraction(@RequestParam double location1Latitude, @RequestParam double location1Longitude,
			@RequestParam double location2Latitude, @RequestParam double location2Longitude, int nearMaxDistance) {
		return gpsService.nearAttraction(
				new Location(location1Latitude, location1Longitude),
				new Location(location2Latitude, location2Longitude),
				nearMaxDistance);

	}

}
