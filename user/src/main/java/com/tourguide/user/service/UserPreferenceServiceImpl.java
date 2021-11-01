package com.tourguide.user.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.user.dao.UserPreferenceDao;
import com.tourguide.user.domain.UserPreferences;

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {
	
	@Autowired
	private UserPreferenceDao userPreferenceDao;
	
	@Override
	public UserPreferences get(UUID userId) {
		return userPreferenceDao.findByUserId(userId);
	}

	@Override
	public UserPreferences get(String userName) {
		return userPreferenceDao.findByUserName(userName);
	}

	@Override
	public UserPreferences save(UserPreferences userPreferences) {
		return userPreferenceDao.save(userPreferences);
	}

	@Override
	public UserPreferences update(UserPreferences userPreferences) {
		return userPreferenceDao.save(userPreferences);
	}

	@Override
	public void delete(UUID userId) {
		userPreferenceDao.delete(userId);
	}

	@Override
	public void delete(String userName) {
		userPreferenceDao.delete(userName);
	}

}
