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
import com.tourguide.gps.controller.dto.VisitedLocationWithUserNameDto;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.proxies.UserProxy;
import com.tourguide.gps.service.GpsService;
import com.tourguide.gps.service.TrackService;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@RestController
public class LocationController {

	@Autowired
	private GpsService gpsService;
	@Autowired
	private TrackService trackService;
	@Autowired
	private RewardProxy rewardProxy;
	@Autowired
	private UserProxy userProxy;

	@RequestMapping("/getLocation")
	public LocationDto getLocation(@RequestParam String userName) {
		LocationDto locationDto;
		locationDto = LocationDto.convertToDto(gpsService.getUserLocation(userName));
		return locationDto;
	}

	@RequestMapping("/getNearbyAttractions")
	public List<NearbyAttractionsDto> getNearbyAttractions(@RequestParam String userName) {
		List<NearbyAttractionsDto> nearbyAttractionsDtoList = new ArrayList<>();
		UUID userId = userProxy.getUserId(userName);
		VisitedLocation visitedLocation = trackService.trackUserLocation(userId, userName);
		List<Attraction> attractionsList = gpsService.getUserNearByAttractions(visitedLocation);
		for (Attraction attraction : attractionsList) {
			Location userLocation = gpsService.getUserLocation(userName);
			int rewardPoint = rewardProxy.getRewardsPoints(attraction.attractionId, userId);
			nearbyAttractionsDtoList.add(new NearbyAttractionsDto(
					userName,
					attraction.latitude, attraction.longitude,
					userLocation.latitude, userLocation.longitude,
					gpsService.getDistance(userLocation, attraction),
					rewardPoint));
		}
		return nearbyAttractionsDtoList;
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
	public void addVisitedLocation(@RequestParam UUID uuid, @RequestParam String userName,
			@RequestParam double latitude,
			@RequestParam double longitude,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timeVisited) {
		gpsService.addVisitedLocation(uuid, userName, new Location(latitude, longitude), timeVisited);
	}

	@RequestMapping("/getVisitedLocations")
	public List<VisitedLocationWithUserNameDto> getVisitedLocations(@RequestParam String userName) {
		List<VisitedLocationWithUserNameDto> visitedLocationDtoList = gpsService.getVisitedLocations(userName).stream()
				.map(visitedLocation -> VisitedLocationWithUserNameDto.convertToDto(visitedLocation, userName))
				.collect(Collectors.toList());
		return visitedLocationDtoList;
	}

	@RequestMapping("/nearAttraction")
	public boolean nearAttraction(@RequestParam double location1Latitude, @RequestParam double location1Longitude,
			@RequestParam double location2Latitude, @RequestParam double location2Longitude,
			@RequestParam int nearMaxDistance) {
		return gpsService.nearAttraction(
				new Location(location1Latitude, location1Longitude),
				new Location(location2Latitude, location2Longitude),
				nearMaxDistance);
	}

	@RequestMapping("/getAttractions")
	public List<AttractionDto> getAttractions() {
		return gpsService.getAttractions().stream()
				.map(attraction -> AttractionDto.convertToDto(attraction))
				.collect(Collectors.toList());
	}

}
