package com.tourguide.gps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourguide.gps.helper.InternalTestHelper;
import com.tourguide.gps.service.GpsService;
import com.tourguide.gps.service.TrackService;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

@SpringBootTest(classes = GpsApplication.class)
public class GpsServiceTest {
	
	@Autowired
	private GpsService gpsService;
	@Autowired
	private TrackService trackService;

//	@Test
//	public void getUserLocation() {
//		String userName = "internalUser25";
//		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
//		
//		InternalTestHelper.setInternalUserNumber(0);
//		
//		VisitedLocation visitedLocation = trackService.trackUserLocation(userId,userName);
//		tourGuideService.tracker.stopTracking();
//		assertTrue(visitedLocation.userId.equals(user.getUserId()));
//	}
	
	@Disabled  // Not yet implemented
	@Test
	public void getNearbyAttractions() {
		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
		InternalTestHelper.setInternalUserNumber(0);
		
		VisitedLocation visitedLocation = trackService.trackUserLocation(userId,userName);
		
		List<Attraction> attractions = gpsService.getNearByAttractions(visitedLocation);
		
//		tourGuideService.tracker.stopTracking();
		
		assertEquals(5, attractions.size());
	}
	
}
