package com.tourguide.testTourGuide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsoniter.output.JsonStream;
import com.tourguide.testTourGuide.proxies.GpsProxy;
import com.tourguide.testTourGuide.proxies.RewardProxy;
import com.tourguide.testTourGuide.proxies.TripProxy;
import com.tourguide.testTourGuide.proxies.UserProxy;

import gpsUtil.location.VisitedLocation;

@RestController
public class TourGuideController {
	
	@Autowired
	private GpsProxy gpsProxy;
	@Autowired
	private RewardProxy rewardProxy;
	@Autowired
	private TripProxy tripProxy;
	@Autowired
	private UserProxy userProxy;

    
    @RequestMapping("/getLocation") 
    public String getLocation(@RequestParam String userName) {
		return gpsProxy.getLocation(userName);
    }
    
    @RequestMapping("/getNearbyAttractions") 
    public String getNearbyAttractions(@RequestParam String userName) {
    	return gpsProxy.getNearbyAttractions(userName);
    }
    
    @RequestMapping("/getRewards") 
    public String getRewards(@RequestParam String userName) {
    	return rewardProxy.getRewards(userProxy.getUserId(userName),userName);
    }
    
    @RequestMapping("/getAllCurrentLocations")
    public String getAllCurrentLocations() {
    	return gpsProxy.getAllCurrentLocations();
    }
    
    @RequestMapping("/getTripDeals")
    public String getTripDeals(@RequestParam String userName) {
    	return tripProxy.getTripDeals(userName);
    }

}