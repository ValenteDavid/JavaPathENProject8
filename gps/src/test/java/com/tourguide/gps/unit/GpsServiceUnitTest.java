package com.tourguide.gps.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tourguide.gps.GpsApplication;
import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.domain.VisitedLocationWithUserName;
import com.tourguide.gps.helper.InternalTestHelper;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.proxies.UserProxy;
import com.tourguide.gps.service.GpsService;
import com.tourguide.gps.service.TrackService;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@SpringBootTest(classes = GpsApplication.class)
public class GpsServiceUnitTest {

	@Autowired
	private GpsService gpsService;
	@MockBean
	private TrackService trackService;
	
	
	@MockBean
	private GpsUtil gpsUtil;
	@MockBean
	private VisitedLocationDao visitedLocationDao;
	
	@MockBean
	private UserProxy userProxy;
	@MockBean
	private RewardProxy rewardProxy;

	@Test
	public void getUserVistedLocation() {
		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
		InternalTestHelper.setInternalUserNumber(0);
		
		List<VisitedLocationWithUserName> listVisitedLocations = new ArrayList<>();
		listVisitedLocations.add(new VisitedLocationWithUserName(UUID.randomUUID(), userId, userName, new Location(0, 0), new Date()));
		when(visitedLocationDao.findByUserName(userName)).thenReturn(listVisitedLocations);
		when(visitedLocationDao.findByUserNameOrderByTimeVisitedDesc(userName)).thenReturn(new VisitedLocation(userId, new Location(0, 0), new Date()));
		
		VisitedLocation visitedLocation = gpsService.getUserVisitedLocation(userName);
		
		assertTrue(visitedLocation.userId.equals(userId));
	}
	
	@Test
	public void getNearbyAttractions_ExpectSize() {
		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
		InternalTestHelper.setInternalUserNumber(0);

		when(gpsUtil.getAttractions()).thenCallRealMethod();
		VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(35, -110), new Date());
		List<Attraction> attractions = gpsService.getUserNearByAttractions(visitedLocation);

		assertEquals(5, attractions.size());
	}

	@Test
	public void getNearbyAttractions_ExpectNearby() {
		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
		InternalTestHelper.setInternalUserNumber(0);

		when(gpsUtil.getAttractions()).thenCallRealMethod();
		Location visitedLocationLocation = new Location(35, -110);
		VisitedLocation visitedLocation = new VisitedLocation(userId, visitedLocationLocation, new Date());
		List<Attraction> attractions = gpsService.getUserNearByAttractions(visitedLocation);

		double maxDistanceList = attractions
				.stream()
				.map(attraction -> gpsService
						.getDistance(new Location(attraction.latitude, attraction.longitude), visitedLocationLocation))
				.sorted(Collections.reverseOrder()).findFirst().get();

		double maxDistanceOther = gpsUtil.getAttractions()
				.stream()
				.map(attraction -> gpsService
						.getDistance(new Location(attraction.latitude, attraction.longitude), visitedLocationLocation))
				.sorted().skip(5).findFirst().get();

		assertTrue(maxDistanceList < maxDistanceOther);
	}

	@Test
	public void isWithinAttractionProximity() {
		Attraction attraction = new Attraction("AttractionTest", "CityTest","StateTest", 0, 0);
		assertTrue(gpsService.isWithinAttractionProximity(attraction, attraction));
	}

}
