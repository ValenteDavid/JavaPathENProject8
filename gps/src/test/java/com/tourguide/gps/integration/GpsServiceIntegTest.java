package com.tourguide.gps.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tourguide.gps.GpsApplication;
import com.tourguide.gps.helper.InternalTestHelper;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.proxies.UserProxy;
import com.tourguide.gps.service.GpsService;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@SpringBootTest(classes = GpsApplication.class)
public class GpsServiceIntegTest {
	
	@Autowired
	private GpsService gpsService;
	
	@MockBean
	private UserProxy userProxy;
	@MockBean
	private RewardProxy rewardProxy;

	@Test
	public void getUserVistedLocation() {
		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
		InternalTestHelper.setInternalUserNumber(0);
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, 5);
		Date date = c.getTime();
		
		gpsService.addVisitedLocation(userId, userName, null, date);
		VisitedLocation visitedLocation = gpsService.getLastVisitedLocation(userName);
		
		System.out.println(date);
		System.out.println(visitedLocation);
		assertTrue(visitedLocation.userId.equals(userId));
		assertTrue(visitedLocation.timeVisited.equals(date));
	}
	
	@Test
	public void getNearbyAttractions() {
		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
		InternalTestHelper.setInternalUserNumber(0);
		
		VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(35, -110), new Date());
		List<Attraction> attractions = gpsService.getUserNearByAttractions(visitedLocation);
		
		assertEquals(5, attractions.size());
	}
	
}
