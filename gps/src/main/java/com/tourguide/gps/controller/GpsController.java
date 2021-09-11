package com.tourguide.gps.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsoniter.output.JsonStream;
import com.tourguide.gps.service.GpsService;

import gpsUtil.location.VisitedLocation;

@RestController
public class GpsController {
	
	@Autowired
	private GpsService gpsService;
	
	  @RequestMapping("/getLocation") 
	    public String getLocation(@RequestParam String userName) {
		  UUID uuid = getUserUUID(userName);
	    	VisitedLocation visitedLocation = gpsService.getUserLocation(uuid);
			return JsonStream.serialize(visitedLocation.location);
	    }
	  
	 private UUID getUserUUID(String UserName) {
		return null;
	 }

}
