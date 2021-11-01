package com.tourguide.reward.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.tourguide.reward.RewardApplication;
import com.tourguide.reward.dao.RewardDao;
import com.tourguide.reward.domain.UserReward;
import com.tourguide.reward.helper.InternalTestHelper;
import com.tourguide.reward.service.RewardsService;

@SpringBootTest(classes = RewardApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class RewardsServiceIntegTest {

	@Autowired
	private RewardsService rewardsService;

	private String userName;
	private UUID userId;

	@BeforeAll
	public void addUserRewards() {
		userName = "internalUser25";
		userId = UUID.nameUUIDFromBytes(userName.getBytes());
		rewardsService.addUserRewards(new UserReward(userId, userName, UUID.randomUUID(), UUID.randomUUID(),"attractionName", 5));
	}

	@Test
	public void userGetRewardsWithUserId() {
		InternalTestHelper.setInternalUserNumber(0);

		List<UserReward> userRewards = rewardsService.getUserRewards(userId);

		assertTrue(userRewards.size() == 1);
	}

	@Test
	public void userGetRewardsWithUserName() {
		InternalTestHelper.setInternalUserNumber(0);

		List<UserReward> userRewards = rewardsService.getUserRewards(userName);

		assertTrue(userRewards.size() == 1);
	}

}
