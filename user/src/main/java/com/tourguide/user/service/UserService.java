package com.tourguide.user.service;

import java.util.List;
import java.util.UUID;

import com.tourguide.user.controller.dto.UserIdName;
import com.tourguide.user.domain.User;

/**
 * User service
 * 
 * @author David
 *
 */
public interface UserService {

	/**
	 * Get user
	 * 
	 * @param userName : user name
	 * @return user
	 * @see User
	 */
	User getUser(String userName);

	/**
	 * Save user
	 * @param user : user
	 * @return user save
	 * @see User
	 */
	User addUser(User user);

	/**
	 * Get user id
	 * @return user id list
	 * @see User
	 */
	List<UUID> getUserId();

	/**
	 * Get all user
	 * @return user list
	 * @see User
	 */
	List<User> getAllUsers();

	/**
	 * Get user id and user name
	 * @return list user id and user name
	 * @see User
	 */
	List<UserIdName> getUserIdentification();

}
