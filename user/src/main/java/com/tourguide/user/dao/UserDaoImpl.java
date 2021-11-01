package com.tourguide.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tourguide.user.controller.dto.UserIdName;
import com.tourguide.user.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	private List<User> users = new ArrayList<User>();

	@Override
	public User findByUserName(String userName) {
		return users.stream()
		.filter(user -> user.getUserName().equals(userName))
		.findFirst()
		.get();
	}

	@Override
	public User save(User user) {
		users.add(user);
		return user;
	}

	@Override
	public List<UUID> findAllId() {
		return users.stream()
				.map(user->user.getUserId())
				.collect(Collectors.toList());
	}

	@Override
	public List<User> getAll() {
		return users;
	}

	@Override
	public List<UserIdName> getUserIdentification() {
		List<UserIdName> returnList = new ArrayList<UserIdName>();
		getAll().stream().forEach(user -> returnList.add(new UserIdName(user.getUserId(), user.getUserName())));
		return returnList;
	}
}
