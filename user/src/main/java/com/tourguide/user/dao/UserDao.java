package com.tourguide.user.dao;

import java.util.List;
import java.util.UUID;

import com.tourguide.user.domain.User;

public interface UserDao {

	User findByUserName(String userName);
	
	User save(User user);

	List<UUID> findAllId();

	List<User> getAll();

}
