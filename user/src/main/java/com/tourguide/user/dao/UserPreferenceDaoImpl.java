package com.tourguide.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.tourguide.user.domain.UserPreferences;

@Repository
public class UserPreferenceDaoImpl implements UserPreferenceDao {
	
	List<UserPreferences> userPreferencesList = new ArrayList<>();
	
	@Override
	public UserPreferences findByUserId(UUID userId) {
		return userPreferencesList.stream()
		.filter(user -> user.getUserId().equals(userId))
		.findFirst()
		.get();
	}
	
	@Override
	public UserPreferences findByUserName(String userName) {
		return userPreferencesList.stream()
		.filter(user -> user.getUserName().equals(userName))
		.findFirst()
		.get();
	}
	
	@Override
	public UserPreferences save(UserPreferences UserPreferences) {
		userPreferencesList.add(UserPreferences);
		return UserPreferences;
	}


	@Override
	public void delete(UUID userId) {
		userPreferencesList.removeIf(user -> user.getUserId().equals(userId));
	}

	@Override
	public void delete(String userName) {
		userPreferencesList.removeIf(user -> user.getUserName().equals(userName));
	}

}
