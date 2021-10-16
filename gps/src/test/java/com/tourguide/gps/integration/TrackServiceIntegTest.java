package com.tourguide.gps.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tourguide.gps.GpsApplication;
import com.tourguide.gps.helper.InternalTestHelper;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.service.TrackService;

import gpsUtil.location.VisitedLocation;

@SpringBootTest(classes = GpsApplication.class)
public class TrackServiceIntegTest {
	
	@Autowired
	private TrackService trackService;
	@MockBean
	private RewardProxy rewardProxy;
	
	@Test
	public void trackUser() {
		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
		InternalTestHelper.setInternalUserNumber(0);
		
		doNothing().when(rewardProxy).calculateRewards(userId, userName);
		VisitedLocation visitedLocation = trackService.trackUserLocation(userId,userName);
		
		assertEquals(userId, visitedLocation.userId);
	}

}
