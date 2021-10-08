package com.tourguide.trip.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.trip.controller.dto.UserPreferenceDto;
import com.tourguide.trip.dao.TripDealDao;
import com.tourguide.trip.domain.TripDeal;
import com.tourguide.trip.proxies.RewardProxy;
import com.tourguide.trip.proxies.UserProxy;

import tripPricer.Provider;
import tripPricer.TripPricer;

@Service
public class TripDealServiceImpl implements TripDealService {
	
	private static final String tripPricerApiKey = "test-server-api-key";
	
	@Autowired
	private TripDealDao tripDealDao;
	
	@Autowired
	private RewardProxy rewardProxy;
	@Autowired
	private UserProxy userProxy;
	
	@Autowired
	private TripPricer tripPricer;

	@Override
	public List<Provider> getTripDeals(String userName) {
		int cumulatativeRewardPoints = rewardProxy.getRewardsPoints(userName);
		UserPreferenceDto userPreference = userProxy.getUserPreference(userName);
		UUID userId = userProxy.getUserId(userName);
		//TODO 
		List<Provider> providers = tripPricer.getPrice(
				tripPricerApiKey, 
				userId,
				userPreference.getNumberOfAdults(),
				userPreference.getNumberOfChildren(),
				userPreference.getTripDuration(),
				cumulatativeRewardPoints);
		tripDealDao.save(new TripDeal(userId,userName,providers) );
		return providers;
	}
	
}
