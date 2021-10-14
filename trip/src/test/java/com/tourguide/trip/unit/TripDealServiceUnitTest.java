package com.tourguide.trip.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import tripPricer.TripPricer;

@SpringBootTest(classes = TripApplication.class)
public class TripDealServiceUnitTest {
	
	@Autowired
	private TripDealService tripDealService;
	
	@MockBean
	private RewardProxy rewardProxy;
	@MockBean
	private UserProxy userProxy;
	
	@MockBean
	private TripPricer tripPricer;
	
	@Test
	public void getTripDeals() {
		String userName = "internalUser25";
		InternalTestHelper.setInternalUserNumber(0);
		
		when(rewardProxy.getRewardsPoints(userName)).thenReturn(10);
		when(userProxy.getUserPreference(userName)).thenReturn(new UserPreferenceDto(5, 3, 2));
		when(userProxy.getUserId(userName)).thenReturn(UUID.nameUUIDFromBytes(userName.getBytes()));
		
		List<Provider> providersReturn  = new ArrayList<Provider>();
		for (int i = 1; i <= 10; i++) {
			providersReturn.add(new Provider(UUID.randomUUID(), "name", 10));
		}
		when(tripPricer.getPrice(anyString(),any(),anyInt(),anyInt(),anyInt(),anyInt())).thenReturn(providersReturn);
		List<Provider> providers = tripDealService.getTripDeals(userName);
		
		assertEquals(10, providers.size());
	}

}
