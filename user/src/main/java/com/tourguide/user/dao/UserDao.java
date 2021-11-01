package com.tourguide.user.dao;

import java.util.List;
import java.util.UUID;

import com.tourguide.user.controller.dto.UserIdName;
import com.tourguide.user.domain.User;

/**
 * User dao
 * @see User
 * @author David
 *
 */
public interface UserDao {

	/**
	 * Find by user name
	 * @param userName : user name
	 * @return user save
	 * @see User
	 */
	User findByUserName(String userName);

	/**
	 * Save user
	 * @param user : user to save
	 * @return
	 * @see User
	 */
	User save(User user);

	/**
	 * Find all user id
	 * @return all user id
	 * @see User
	 */
	List<UUID> findAllId();

	/**
	 * Get all
	 * @return list user
	 * @see User
	 */
	List<User> getAll();

	/**
	 * Get user id and user name
	 * @return list user id and user name
	 * @see User
	 */
	List<UserIdName> getUserIdentification();

}
