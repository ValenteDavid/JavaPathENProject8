package com.tourguide.gps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourguide.gps.helper.InternalTestHelper;
import com.tourguide.gps.service.TrackService;

import gpsUtil.location.VisitedLocation;

@SpringBootTest(classes = GpsApplication.class)
public class TrackServiceTest {
	
	@Autowired
	private TrackService trackService;
	
	@Test
	public void trackUser() {
		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
		
		InternalTestHelper.setInternalUserNumber(0);
		
		VisitedLocation visitedLocation = trackService.trackUserLocation(userId,userName);
		
//		tourGuideService.tracker.stopTracking();
		
		assertEquals(userId, visitedLocation.userId);
	}

}
