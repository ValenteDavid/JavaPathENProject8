package com.tourguide.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tourguide.user.domain.UserPreferences;

@Repository
public class UserPreferenceDaoImpl implements UserPreferenceDao {
	
	List<UserPreferences> userPreferencesList = new ArrayList<>();

	@Override
	public UserPreferences findByUserName(String userName) {
		return userPreferencesList.stream()
		.filter(user -> user.getUserName().equals(userName))
		.findFirst()
		.get();
	}

}
