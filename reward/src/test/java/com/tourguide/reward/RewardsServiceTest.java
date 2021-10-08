package com.tourguide.reward;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourguide.reward.helper.InternalTestHelper;
import com.tourguide.reward.service.RewardsService;

@SpringBootTest(classes = RewardApplication.class)
public class RewardsServiceTest {
	
	@Autowired
	private RewardsService rewardsService;
	
	@Test
	public void userGetRewards() {
		InternalTestHelper.setInternalUserNumber(0);
		
//		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
//		Attraction attraction = gpsUtil.getAttractions().get(0);
//		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));
//		tourGuideService.trackUserLocation(user);
//		
//		List<UserReward> userRewards = rewardsService.getUserRewards();
////		tourGuideService.tracker.stopTracking();
//		
//		assertTrue(userRewards.size() == 1);
	}

}
