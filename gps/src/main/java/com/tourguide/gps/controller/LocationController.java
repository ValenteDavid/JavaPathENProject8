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

import com.tourguide.gps.controller.dto.AttractionDto;
import com.tourguide.gps.controller.dto.CurrentLocationDto;
import com.tourguide.gps.controller.dto.LocationDto;
import com.tourguide.gps.controller.dto.NearbyAttractionsDto;
import com.tourguide.gps.controller.dto.VisitedLocationWithUserNameDto;
import com.tourguide.gps.domain.VisitedLocationWithUserName;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.proxies.UserProxy;
import com.tourguide.gps.service.GpsService;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

/**
 * Controller
 * @author David
 *
 */
@RestController
public class LocationController {

	/**
	 * @see GpsService
	 */
	@Autowired
	private GpsService gpsService;
	/** 
	 * @see RewardProxy
	 */ 
	@Autowired
	private RewardProxy rewardProxy;
	/**
	 * @see UserProxy
	 */
	@Autowired
	private UserProxy userProxy;

	/**
	 * Endpoint getLocation
	 * 
	 * @param userName : The name of User
	 * @return the location of user
	 * 
	 * @see com.tourguide.user.domain.User
	 */
	@RequestMapping("/getLocation")
	public LocationDto getLocation(@RequestParam String userName) {
		LocationDto locationDto;
		locationDto = LocationDto.convertToDto(gpsService.getUserLocation(userName));
		return locationDto;
	}

	/**
	 * Endpoint getNearbyAttractions
	 * 
	 * @param userName : The name of User
	 * @return the 5 nearby attraction
	 * 
	 * @see com.tourguide.user.domain.User
	 */
	@RequestMapping("/getNearbyAttractions")
	public List<NearbyAttractionsDto> getNearbyAttractions(@RequestParam String userName) {
		List<NearbyAttractionsDto> nearbyAttractionsDtoList = new ArrayList<>();
		UUID userId = userProxy.getUserId(userName);
		VisitedLocation visitedLocation = gpsService.getUserVisitedLocation(userName);
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

	/**
	 * Endpoint getAllCurrentLocations
	 * 
	 * @return Current locations of all users
	 */
	@RequestMapping("/getAllCurrentLocations")
	public List<CurrentLocationDto> getAllCurrentLocations() {
		List<CurrentLocationDto> allCurrentLocations = new ArrayList<CurrentLocationDto>();
		userProxy.getUserIdAndUserNameAll().stream()
		.forEach(userIdName-> allCurrentLocations.add(new CurrentLocationDto(userIdName.getUserId(),gpsService.getLastLocation(userIdName.getUserName()))));
		System.out.println(allCurrentLocations.size());
		return allCurrentLocations;
	}

	/**
	 * Endpoint addVisitedLocation
	 * 
	 * @param userId : id of user
	 * @param userName : name of user
	 * @param latitude : latitde of location
	 * @param longitude : longitude of location
	 * @param timeVisited : time visietd location
	 * 
	 * @see com.tourguide.user.domain.User
	 * @see Location
	 * @see VisitedLocationWithUserName
	 */
	@RequestMapping("/addVisitedLocation")
	public void addVisitedLocation(@RequestParam UUID userId, @RequestParam String userName,
			@RequestParam double latitude,
			@RequestParam double longitude,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timeVisited) {
		gpsService.addVisitedLocation(userId, userName, new Location(latitude, longitude), timeVisited);
	}

	/**
	 * Endpoint getVisitedLocations
	 * 
	 * @param userName
	 * @return
	 * @see VisitedLocationWithUserNameDto
	 */
	@RequestMapping("/getVisitedLocations")
	public List<VisitedLocationWithUserNameDto> getVisitedLocations(@RequestParam String userName) {
		List<VisitedLocationWithUserNameDto> visitedLocationDtoList = gpsService.getVisitedLocations(userName).stream()
				.map(visitedLocation -> VisitedLocationWithUserNameDto.convertToDto(visitedLocation, userName))
				.collect(Collectors.toList());
		return visitedLocationDtoList;
	}

	/**
	 * Endpoint nearAttraction
	 * 
	 * @param location1Latitude : the latitude of location 1
	 * @param location1Longitude : the longitude of location 1
	 * @param location2Latitude : the latitude of location 2
	 * @param location2Longitude : the longitude of location 2
	 * @param nearMaxDistance : maximum distance between location 1 and 2
	 * @return true if distance < nearMaxDistance else false
	 */
	@RequestMapping("/nearAttraction")
	public boolean nearAttraction(@RequestParam double location1Latitude, @RequestParam double location1Longitude,
			@RequestParam double location2Latitude, @RequestParam double location2Longitude,
			@RequestParam int nearMaxDistance) {
		return gpsService.nearAttraction(
				new Location(location1Latitude, location1Longitude),
				new Location(location2Latitude, location2Longitude),
				nearMaxDistance);
	}

	/**
	 * Endpoint getAttractions
	 * @return all attractions
	 */
	@RequestMapping("/getAttractions")
	public List<AttractionDto> getAttractions() {
		return gpsService.getAttractions().stream()
				.map(attraction -> AttractionDto.convertToDto(attraction))
				.collect(Collectors.toList());
	}
	
	/**
	 * Endpoint delete all VisitedLocationWithUserName
	 * @see VisitedLocationWithUserName
	 */
	@RequestMapping("/deleteAll")
	public void deleteAll() {
		gpsService.deleteAll();
	}

}
