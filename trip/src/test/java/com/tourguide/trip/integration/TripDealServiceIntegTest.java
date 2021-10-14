package com.tourguide.trip.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tourguide.trip.TripApplication;
import com.tourguide.trip.controller.dto.UserPreferenceDto;
import com.tourguide.trip.helper.InternalTestHelper;
import com.tourguide.trip.proxies.RewardProxy;
import com.tourguide.trip.proxies.UserProxy;
import com.tourguide.trip.service.TripDealService;

import tripPricer.Provider;

@SpringBootTest(classes = TripApplication.class)
public class TripDealServiceIntegTest {
	
	@Autowired
	private TripDealService tripDealService;
	
	@MockBean
	private RewardProxy rewardProxy;
	@MockBean
	private UserProxy userProxy;
	
	@Test
	public void getTripDeals() {
		String userName = "internalUser25";
		InternalTestHelper.setInternalUserNumber(0);
		
		when(rewardProxy.getRewardsPoints(userName)).thenReturn(10);
		when(userProxy.getUserPreference(userName)).thenReturn(new UserPreferenceDto(1, 2, 2));
		when(userProxy.getUserId(userName)).thenReturn(UUID.nameUUIDFromBytes(userName.getBytes()));
		
		List<Provider> providers = tripDealService.getTripDeals(userName);
		
		assertEquals(10, providers.size());
	}

}
