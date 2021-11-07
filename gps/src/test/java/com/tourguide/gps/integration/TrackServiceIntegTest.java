package com.tourguide.gps.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tourguide.gps.GpsApplication;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.service.GpsService;
import com.tourguide.gps.service.TrackService;

import gpsUtil.location.VisitedLocation;

@SpringBootTest(classes = GpsApplication.class)
public class TrackServiceIntegTest {
	
	@Autowired
	private TrackService trackService;
	
	@Autowired
	private GpsService gpsService;
	
	@MockBean
	private RewardProxy rewardProxy;
	
	@Test
	public void trackUser() {
		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
		
		doNothing().when(rewardProxy).calculateRewards(any(), any(),anyList(),anyList());
		trackService.trackUserLocation(userId,userName);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VisitedLocation visitedLocation= gpsService.getLastVisitedLocation(userName);
		
		assertEquals(userId, visitedLocation.userId);
	}

}
