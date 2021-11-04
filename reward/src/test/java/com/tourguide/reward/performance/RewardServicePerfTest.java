package com.tourguide.reward.performance;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tourguide.reward.RewardApplication;
import com.tourguide.reward.controller.dto.AttractionDto;
import com.tourguide.reward.controller.dto.UserIdName;
import com.tourguide.reward.dao.RewardDao;
import com.tourguide.reward.helper.InternalTestHelper;
import com.tourguide.reward.proxies.GpsProxy;
import com.tourguide.reward.proxies.UserProxy;
import com.tourguide.reward.service.RewardsService;

@SpringBootTest(classes = RewardApplication.class)
public class RewardServicePerfTest {
	
	@Autowired
	private GpsProxy gpsProxy;
	@Autowired
	private UserProxy userProxy;
	
	@Autowired
	private RewardDao rewardDao;
	
	@Autowired
	private RewardsService rewardsService;
	
	@Test
	public void highVolumeGetRewards() {
		// Users should be incremented up to 100,000, and test finishes within 20 minutes
		InternalTestHelper.setInternalUserNumber(100000);
		System.out.println(InternalTestHelper.getInternalUserNumber());
		AttractionDto attraction =  gpsProxy.getAttractions().get(0);
		
		List<UserIdName> allUsers = userProxy.getUserIdAndUserNameAll();
		allUsers=allUsers.subList(0,InternalTestHelper.getInternalUserNumber());
		
		gpsProxy.deleteAll();
		allUsers.forEach(u -> gpsProxy.addVisitedLocation(u.getUserId(),u.getUserName(), attraction.getLatitude(), attraction.getLongitude(),  new Date()));
		
		rewardsService.deleteAll();
		System.out.println("---------START---------");
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		for(UserIdName userIdName : allUsers) {
			rewardsService.calculateRewards(userIdName.getUserId(),userIdName.getUserName());
		}
		
		boolean flag = true;
		while (flag) {
			flag = false;
			for(UserIdName userIdName : allUsers) {
				if (rewardsService.getUserRewards(userIdName.getUserName()).size()<0) {
					flag = true;
				};
			}
			System.out.println(rewardDao.getUserRewards().size());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("FIN");
		System.out.println(rewardDao.getUserRewards().size());
		stopWatch.stop();

		System.out.println("highVolumeGetRewards: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds."); 
		assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}

}
