package com.tourguide.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.user.dao.UserDao;
import com.tourguide.user.dao.UserPreferenceDao;
import com.tourguide.user.domain.User;
import com.tourguide.user.domain.UserPreferences;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserPreferenceDao userPreferenceDao;

	@Override
	public User getUser(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public User addUser(User user) {
		return userDao.save(user);
	}

	@Override
	public UserPreferences getUserPreference(String userName) {
		return userPreferenceDao.findByUserName(userName);
	}

	@Override
	public List<UUID> getUserId() {
		return userDao.findAllId();
	}

}
