package com.tourguide.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourguide.trip.helper.InternalTestHelper;
import com.tourguide.trip.service.TripDealService;

import tripPricer.Provider;

@SpringBootTest(classes = TripApplication.class)
public class TripDealServiceTest {
	
	@Autowired
	private TripDealService tripDealService;
	
	@Test
	public void getTripDeals() {
		String userName = "internalUser25";
		InternalTestHelper.setInternalUserNumber(0);
		
		List<Provider> providers = tripDealService.getTripDeals(userName);
		
//		tourGuideService.tracker.stopTracking();
		
		assertEquals(10, providers.size());
	}

}
