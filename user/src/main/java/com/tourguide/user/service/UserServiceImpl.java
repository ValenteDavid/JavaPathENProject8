package com.tourguide.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.user.dao.UserDao;
import com.tourguide.user.domain.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public User addUser(User user) {
		return userDao.save(user);
	}

}
