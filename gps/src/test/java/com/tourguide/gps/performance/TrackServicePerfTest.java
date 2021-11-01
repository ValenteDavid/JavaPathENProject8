package com.tourguide.gps.performance;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourguide.gps.GpsApplication;
import com.tourguide.gps.controller.dto.UserIdName;
import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.domain.VisitedLocationWithUserName;
import com.tourguide.gps.helper.InternalTestHelper;
import com.tourguide.gps.proxies.UserProxy;
import com.tourguide.gps.service.TrackService;

@SpringBootTest(classes = GpsApplication.class)
public class TrackServicePerfTest {

	@Autowired
	private TrackService trackService;
	
	@Autowired
	private VisitedLocationDao visitedLocationDao;

	@Autowired
	private UserProxy userProxy;
	
	@Disabled
	@Test
	public void highVolumeTrackLocation() {
		// Users should be incremented up to 100,000, and test finishes within 15 minutes
		InternalTestHelper.setInternalUserNumber(100000);

		List<UserIdName> allUsers = userProxy.getUserIdAndUserNameAll();
		allUsers=allUsers.subList(0,InternalTestHelper.getInternalUserNumber());
		visitedLocationDao.setVisitedLocationsWithUserNameList(new ArrayList<VisitedLocationWithUserName>());
		
	    StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		
		for(UserIdName userIdName : allUsers) {
			trackService.trackUserLocation(userIdName.getUserId(),userIdName.getUserName());
		}
		while (visitedLocationDao.getVisitedLocationsWithUserNameList().size()<InternalTestHelper.getInternalUserNumber()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Before Stop " + visitedLocationDao.getVisitedLocationsWithUserNameList().size());
		stopWatch.stop();

		System.out.println("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds."); 
		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}
	
}
