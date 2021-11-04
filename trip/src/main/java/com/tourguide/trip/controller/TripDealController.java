package com.tourguide.trip.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.trip.service.TripDealService;

import tripPricer.Provider;
/**
 * Trip deal controller
 * @author David
 *
 */
@RestController
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
	    public List<Provider> getTripDeals(@RequestParam String userName,@RequestParam UUID attractionId) {
	    	List<Provider> providers = tripDealService.getTripDeals(userName,attractionId);
	    	return providers;
	    }
	
}
