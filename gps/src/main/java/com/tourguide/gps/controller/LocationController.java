package com.tourguide.gps.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsoniter.output.JsonStream;
import com.tourguide.gps.controller.dto.AttractionDto;
import com.tourguide.gps.controller.dto.LocationDto;
import com.tourguide.gps.controller.dto.NearbyAttractionsDto;
import com.tourguide.gps.controller.dto.VisitedLocationDto;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.service.GpsService;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

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
	
	 //  TODO: Change this method to no longer return a List of Attractions.
 	//  Instead: Get the closest five tourist attractions to the user - no matter how far away they are.
 	//  Return a new JSON object that contains:
    	// Name of Tourist attraction, 
        // Tourist attractions lat/long, 
        // The user's location lat/long, 
        // The distance in miles between the user's location and each of the attractions.
        // The reward points for visiting each Attraction.
        //    Note: Attraction reward points can be gathered from RewardsCentral
	@RequestMapping("/getNearbyAttractions")
	public List<Attraction> getNearbyAttractions(@RequestParam String userName) {
		VisitedLocation visitedLocation = gpsService.getUserVisitedLocation(userName);
		List<Attraction> nearbyAttractionsList = gpsService.getUserNearByAttractions(visitedLocation);
		return nearbyAttractionsList;
	}
	
	@RequestMapping("/getAllCurrentLocations")
	public String getAllCurrentLocations() {
		// TODO: Get a list of every user's most recent location as JSON
		// - Note: does not use gpsUtil to query for their current location,
		// but rather gathers the user's current location from their stored location
		// history.
		//
		// Return object should be the just a JSON mapping of userId to Locations
		// similar to:
		// {
		// "019b04a9-067a-4c76-8817-ee75088c3822":
		// {"longitude":-48.188821,"latitude":74.84371}
		// ...
		// }

		return JsonStream.serialize("");
	}

	@RequestMapping("/addVisitedLocation")
	public void addVisitedLocation(@RequestParam UUID uuid,@RequestParam String userName, @RequestParam double latitude,
			@RequestParam double longitude,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timeVisited) {
		gpsService.addVisitedLocation(uuid,userName, new Location(latitude, longitude), timeVisited);
	}

	@RequestMapping("/getVisitedLocations")
	public List<VisitedLocationDto> getVisitedLocations(@RequestParam String userName) {
		List<VisitedLocationDto> visitedLocationDtoList = gpsService.getVisitedLocations(userName).stream()
				.map(visitedLocation -> VisitedLocationDto.convertToDto(visitedLocation))
				.collect(Collectors.toList());
		return visitedLocationDtoList;
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
