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
	@Autowired
	private RewardProxy rewardProxy;

	@RequestMapping("/getLocation")
	public LocationDto getLocation(@RequestParam String userName) {
		LocationDto locationDto;
		locationDto = LocationDto.convertToDto(gpsService.getUserLocation(userName));
		return locationDto;
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

	@RequestMapping("/getNearbyAttractions")
	public List<NearbyAttractionsDto> getNearbyAttractions(@RequestParam String userName) {
		List<NearbyAttractionsDto> nearbyAttractionsDtoList = new ArrayList<>();

		List<Attraction> attractionsList = gpsService.getUserNearByAttractions(userName);
		int maxNb = attractionsList.size()>=5?4:attractionsList.size();
		for (Attraction attraction : attractionsList.subList(0, maxNb)) {

			Location userLocation = gpsService.getUserLocation(userName);
//			int rewardPoint = rewardProxy.getRewards(userName);
			int rewardPoint = 0;
			nearbyAttractionsDtoList.add(new NearbyAttractionsDto(
					userName,
					attraction.latitude, attraction.longitude,
					userLocation.latitude, userLocation.longitude,
					gpsService.getDistance(userLocation, attraction),
					rewardPoint));
		}
		return nearbyAttractionsDtoList;
	}

}
