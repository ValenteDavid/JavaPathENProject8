package com.tourguide.user.dao;

import com.tourguide.user.domain.User;

public interface UserDao {

	User findByUserName(String userName);
	
	User save(User user);

}
