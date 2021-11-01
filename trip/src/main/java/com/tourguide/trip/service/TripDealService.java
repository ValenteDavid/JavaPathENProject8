package com.tourguide.trip.service;

import java.util.List;

import tripPricer.Provider;

/**
 * Trip deal service
 * @author David
 *
 */
public interface TripDealService {

	/**
	 * Get trip deals
	 * @param userName
	 * @return 
	 * @See Provider
	 */
	List<Provider> getTripDeals(String userName);

}
