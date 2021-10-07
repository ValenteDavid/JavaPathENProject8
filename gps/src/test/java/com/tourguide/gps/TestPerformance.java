package com.tourguide.gps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourguide.gps.herlper.InternalTestHelper;
import com.tourguide.gps.proxies.UserProxy;
import com.tourguide.gps.service.TrackService;

import gpsUtil.GpsUtil;

@SpringBootTest(classes = GpsApplication.class)
public class TestPerformance {

	@Autowired
	private GpsUtil gpsUtil;
	@Autowired
	private UserProxy userProxy;

	@Autowired
	private TrackService trackService;

	@Test
	public void highVolumeTrackLocation() {
		InternalTestHelper.setInternalUserNumber(500);
		List<String> allUsersUserName = new ArrayList<>();
		
		IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {
			allUsersUserName.add("internalUser" + i);
		});

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for (String userName : allUsersUserName) {
			trackService.trackUserLocation(UUID.nameUUIDFromBytes(userName.getBytes()), userName);
		}
		stopWatch.stop();
//		tourGuideService.tracker.stopTracking();

		System.out.println("highVolumeTrackLocation: Time Elapsed: "
				+ TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}

}
