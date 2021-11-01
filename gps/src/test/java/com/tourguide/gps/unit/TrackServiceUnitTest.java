package com.tourguide.gps.unit;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tourguide.gps.GpsApplication;
import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.helper.InternalTestHelper;
import com.tourguide.gps.proxies.RewardProxy;
import com.tourguide.gps.service.TrackService;

import gpsUtil.GpsUtil;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@SpringBootTest(classes = GpsApplication.class)
public class TrackServiceUnitTest {
	
	@Autowired
	private TrackService trackService;
	@MockBean
	private RewardProxy rewardProxy;
	@MockBean
	private VisitedLocationDao visitedLocationDao;
	@MockBean
	private GpsUtil gpsUtil;
	
	@Test
	public void trackUser() {
		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
		InternalTestHelper.setInternalUserNumber(0);
		
		doNothing().when(rewardProxy).calculateRewards(userId, userName);
		VisitedLocation visitedLocation =new VisitedLocation(userId, new Location(0, 0), new Date());
		when(gpsUtil.getUserLocation(userId)).thenReturn(visitedLocation);
		trackService.trackUserLocation(userId,userName);
		
		verify(gpsUtil,times(1)).getUserLocation(userId);
		verify(rewardProxy,times(1)).calculateRewards(userId,userName);
		
	}

}
