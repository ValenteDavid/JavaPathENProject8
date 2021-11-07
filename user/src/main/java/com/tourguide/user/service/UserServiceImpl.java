package com.tourguide.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.user.controller.dto.UserIdName;
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

	@Override
	public List<UUID> getUserId() {
		return userDao.findAllId();
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAll();
	}

	@Override
	public List<UserIdName> getUserIdentification() {
		return userDao.getUserIdentification();
	}

}
