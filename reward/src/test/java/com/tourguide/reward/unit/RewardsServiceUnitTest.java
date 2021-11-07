package com.tourguide.reward.unit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
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

import com.tourguide.reward.RewardApplication;
import com.tourguide.reward.controller.dto.AttractionDto;
import com.tourguide.reward.controller.dto.VisitedLocationWithUserNameDto;
import com.tourguide.reward.dao.RewardDao;
import com.tourguide.reward.domain.UserReward;
import com.tourguide.reward.helper.InternalTestHelper;
import com.tourguide.reward.proxies.GpsProxy;
import com.tourguide.reward.service.RewardsService;

@SpringBootTest(classes = RewardApplication.class)
public class RewardsServiceUnitTest {

	@Autowired
	private RewardsService rewardsService;

	@MockBean
	private RewardDao rewardDao;

	@MockBean
	private GpsProxy gpsProxy;

	@Test
	public void userGetRewardsWithUserId() {
		InternalTestHelper.setInternalUserNumber(0);

		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());

		List<UserReward> rewardsReturn = new ArrayList<UserReward>();
		rewardsReturn.add(new UserReward(userId, userName, UUID.randomUUID(), UUID.randomUUID(),"attractionName", 5));

		when(rewardDao.findByUserId(userId)).thenReturn(rewardsReturn);
		List<UserReward> userRewards = rewardsService.getUserRewards(userId);

		assertTrue(userRewards.size() == 1);
	}

	@Test
	public void userGetRewardsWithUserName() {
		InternalTestHelper.setInternalUserNumber(0);

		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());

		List<UserReward> rewardsReturn = new ArrayList<UserReward>();
		rewardsReturn.add(new UserReward(userId, userName, UUID.randomUUID(), UUID.randomUUID(),"attractionName", 5));

		when(rewardDao.findByUserName(userName)).thenReturn(rewardsReturn);
		List<UserReward> userRewards = rewardsService.getUserRewards(userName);

		assertTrue(userRewards.size() == 1);
	}

	@Test
	public void calculateRewardsTest() {
		InternalTestHelper.setInternalUserNumber(0);
		rewardsService.setAttractionProximityRange(Integer.MAX_VALUE);

		String userName = "internalUser25";
		UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());

		List<VisitedLocationWithUserNameDto> userVisitedLocations = new ArrayList<VisitedLocationWithUserNameDto>();
		userVisitedLocations.add(new VisitedLocationWithUserNameDto(UUID.randomUUID(), userName, userId, 100, 100, new Date()));
		when(gpsProxy.getVisitedLocations(userName)).thenReturn(userVisitedLocations);
		
		List<AttractionDto> attractions = new ArrayList<AttractionDto>();
		attractions.add(new AttractionDto("attractionName", "city", "state", UUID.randomUUID(), 100, 100));
		when(gpsProxy.getAttractions()).thenReturn(attractions);
		
		when(gpsProxy.nearAttraction(anyDouble(), anyDouble(), anyDouble(), anyDouble(), anyInt()))
		.thenReturn(true);
		
		rewardsService.calculateRewards(userId, userName);
		
		verify(gpsProxy,times(1)).getVisitedLocations(userName);
		verify(gpsProxy,times(1)).getAttractions();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		verify(rewardDao,atLeastOnce()).save(any());

	}

}
