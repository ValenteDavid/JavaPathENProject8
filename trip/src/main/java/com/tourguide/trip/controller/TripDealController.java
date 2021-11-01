package com.tourguide.trip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsoniter.output.JsonStream;
import com.tourguide.trip.service.TripDealService;

import tripPricer.Provider;
/**
 * Trip deal controller
 * @author David
 *
 */
@Controller
public class TripDealController {

	/**
	 * @see TripDealService
	 */
	@Autowired
	private TripDealService tripDealService;
	
	/**
	 * Endpoint /getTripDeals
	 * @param userName : user name
	 * @return provider list
	 * @see com.tourguide.user.domain.User
	 */
	 @RequestMapping("/getTripDeals")
	    public String getTripDeals(@RequestParam String userName) {
	    	List<Provider> providers = tripDealService.getTripDeals(userName);
	    	return JsonStream.serialize(providers);
	    }
	
}
