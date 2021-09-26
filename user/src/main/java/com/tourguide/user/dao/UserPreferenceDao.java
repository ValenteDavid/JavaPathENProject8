package com.tourguide.user.dao;

import com.tourguide.user.domain.UserPreferences;

public interface UserPreferenceDao {

	UserPreferences findByUserName(String userName);

}
