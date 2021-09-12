package com.tourguide.user.service;

import com.tourguide.user.domain.User;

public interface UserService {

	User getUser(String userName);

	User addUser(User user);

}
