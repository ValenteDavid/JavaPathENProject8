package com.tourguide.gps.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tourguide.gps.GpsApplication;
import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.proxies.UserProxy;

import gpsUtil.GpsUtil;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@SpringBootTest(classes = GpsApplication.class)
class GpsServiceImplTest {

	@Autowired
	private GpsService gpsService;

	@MockBean
	private UserProxy userProxy;
	@MockBean
	private TrackService trackService;
	@MockBean
	private GpsUtil gpsUtil;
	@MockBean
	private VisitedLocationDao visitedLocationDao;

	@Test
	void getUserLocation_visitedLocationSizeNotEqualZero() {
		String userName = "jon";
		UUID userId = UUID.randomUUID();

		VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(1, 1), new Date());
		List<VisitedLocation> visitedLocationList = new ArrayList<>();
		visitedLocationList.add(new VisitedLocation(userId, new Location(2, 2), new Date()));
		
		
		when(userProxy.getUserId(userName)).thenReturn(userId);
		when(visitedLocationDao.findByUUID(userId)).thenReturn(visitedLocationList);
		when(visitedLocationDao.findByUUIDOrderByTimeVisitedDesc(userId)).thenReturn(visitedLocation);
		
		Location location = gpsService.getUserLocation(userName);

		verify(visitedLocationDao,times(1)).findByUUIDOrderByTimeVisitedDesc(userId);
		assertTrue(visitedLocation.userId.equals(userId));
		assertNotNull(location);
	}

	@Test
	void getUserLocation_visitedLocationSizeEqualZero() {
		String userName = "jon";
		UUID userId = UUID.randomUUID();
		
		VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(1, 1), new Date());

		when(userProxy.getUserId(userName)).thenReturn(userId);
		when(visitedLocationDao.findByUUID(userId)).thenReturn(new ArrayList<VisitedLocation>());
		when(trackService.trackUserLocation(userName)).thenReturn(visitedLocation);
		Location location = gpsService.getUserLocation(userName);

		verify(trackService,times(1)).trackUserLocation(userName);
		assertTrue(visitedLocation.userId.equals(userId));
		assertNotNull(location);
	}

}
