package com.tourguide.gps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tourguide.gps.GpsApplication;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.proxies.UserProxy;

import gpsUtil.GpsUtil;
import gpsUtil.location.VisitedLocation;

@SpringBootTest(classes = GpsApplication.class)
class TrackServiceImplTest {

	@Autowired
	private TrackService trackService;

	@MockBean
	private UserProxy userProxy;
	@MockBean
	private RewardProxy rewardProxy;
	@MockBean
	private GpsUtil gpsUtil;

	@Test
	public void trackUser() {
		String userName = "jon";
		UUID userId = UUID.randomUUID();
		VisitedLocation visitedLocationCreate = new VisitedLocation(userId, null, null);

		when(userProxy.getUserId(userName)).thenReturn(userId);
		when(gpsUtil.getUserLocation(userId)).thenReturn(visitedLocationCreate);
		doNothing().when(rewardProxy).calculateRewards(userId,any(),any());
		
//		InternalTestHelper.setInternalUserNumber(0);

		
		VisitedLocation visitedLocation = trackService.trackUserLocation(userId, "jon");
//		tourGuideService.tracker.stopTracking();

		assertEquals(userId, visitedLocation.userId);
	}

}
