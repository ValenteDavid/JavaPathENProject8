package com.tourguide.trip.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tourguide.trip.domain.TripDeal;

@Repository
public class TripDealDaoImpl implements TripDealDao {
	
	List<TripDeal> tripDealList = new ArrayList<>();

	@Override
	public void save(TripDeal tripDeal) {
		tripDealList.add(tripDeal);
	}

}
