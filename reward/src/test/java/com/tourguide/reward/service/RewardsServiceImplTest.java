package com.tourguide.reward.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourguide.reward.RewardApplication;

@SpringBootTest(classes = RewardApplication.class)
class RewardsServiceImplTest {
	
	 @Autowired
	 private RewardsService rewardsService;

}
