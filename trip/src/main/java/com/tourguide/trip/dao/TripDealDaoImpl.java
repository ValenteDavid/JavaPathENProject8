package com.tourguide.trip.dao;

import java.util.ArrayList;
import java.util.List;

import com.tourguide.trip.domain.TripDeal;

public class TripDealDaoImpl implements TripDealDao {
	
	List<TripDeal> tripDealList = new ArrayList<>();

	@Override
	public void save(TripDeal tripDeal) {
		tripDealList.add(tripDeal);
	}

}
