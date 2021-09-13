package com.tourguide.reward.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.reward.bean.VisitedLocationBean;
import com.tourguide.reward.dao.RewardDao;
import com.tourguide.reward.domain.UserReward;
import com.tourguide.reward.proxies.GpsProxy;
import com.tourguide.reward.proxies.UserProxy;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;

@Service
public class RewardsServiceImpl implements RewardsService{
	private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
	
	// proximity in miles
    private int defaultProximityBuffer = 10;
	private int proximityBuffer = defaultProximityBuffer;
	private int attractionProximityRange = 200;
	
	@Autowired
	private GpsUtil gpsUtil;
	@Autowired
	private UserProxy userProxy;
	@Autowired
	private GpsProxy gpsProxy;
	
	@Autowired
	private RewardDao rewardDao;
	
	@Autowired
	private RewardCentral rewardsCentral;
	
	public void calculateRewards(String userName) {
		List<VisitedLocation> userLocations = 
				gpsProxy.getVisitedLocations(userName).stream()
				.map(visitedLocationBean -> VisitedLocationBean.convertToDomain(visitedLocationBean))
				.collect(Collectors.toList());
				
		List<Attraction> attractions = gpsUtil.getAttractions();
		
		for(VisitedLocation visitedLocation : userLocations) {
			for(Attraction attraction : attractions) {
				if(getUserRewards(userName).stream().filter(r -> r.attraction.attractionName.equals(attraction.attractionName)).count() == 0) {
					if(nearAttraction(visitedLocation, attraction)) {
						addUserRewards(new UserReward(visitedLocation, attraction, getRewardPoints(attraction, userName)));
					}
				}
			}
		}
	}
	
	public List<UserReward> getUserRewards(String userName) {
		return rewardDao.findByUserId(userProxy.getUserId(userName));
	}
	
	public UserReward addUserRewards(UserReward userReward) {
		return rewardDao.save(userReward);
	}
	
	private boolean nearAttraction(VisitedLocation visitedLocation, Attraction attraction) {
		return getDistance(attraction, visitedLocation.location) > proximityBuffer ? false : true;
	}
	
	private int getRewardPoints(Attraction attraction, String userName) {
		return rewardsCentral.getAttractionRewardPoints(attraction.attractionId, userProxy.getUserId(userName));
	}
	
	public double getDistance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.latitude);
        double lon1 = Math.toRadians(loc1.longitude);
        double lat2 = Math.toRadians(loc2.latitude);
        double lon2 = Math.toRadians(loc2.longitude);

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                               + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
	}

}
