package com.tourguide.trip.dao;

import com.tourguide.trip.domain.TripDeal;

/**
 * Trip deal dao
 * @author David
 *
 */
public interface TripDealDao {

	/**
	 * Save TripDeal 
	 * @param tripDeal : trip deal save
	 * @See TripDeal
	 */
	void save(TripDeal tripDeal);

}
