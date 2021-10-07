package com.tourguide.user.service;

import java.util.List;
import java.util.UUID;

import com.tourguide.user.controller.dto.UserPreferenceDto;
import com.tourguide.user.domain.User;
import com.tourguide.user.domain.UserPreferences;

public interface UserService {

	User getUser(String userName);

	User addUser(User user);

	UserPreferences getUserPreference(String userName);

	List<UUID> getUserId();

}
