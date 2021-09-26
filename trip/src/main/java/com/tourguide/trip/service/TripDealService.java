package com.tourguide.trip.service;

import java.util.List;

import tripPricer.Provider;

public interface TripDealService {

	List<Provider> getTripDeals(String userName);

}
