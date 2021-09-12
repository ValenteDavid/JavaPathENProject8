package com.tourguide.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

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

}
