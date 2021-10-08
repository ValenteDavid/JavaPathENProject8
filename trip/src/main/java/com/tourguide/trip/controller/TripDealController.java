package com.tourguide.trip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsoniter.output.JsonStream;
import com.tourguide.trip.service.TripDealService;

import tripPricer.Provider;

@Controller
public class TripDealController {

	@Autowired
	private TripDealService tripDealService;
	
	 @RequestMapping("/getTripDeals")
	    public String getTripDeals(@RequestParam String userName) {
	    	List<Provider> providers = tripDealService.getTripDeals(userName);
	    	return JsonStream.serialize(providers);
	    }
	
}
