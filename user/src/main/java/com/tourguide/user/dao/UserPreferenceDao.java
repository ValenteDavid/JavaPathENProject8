package com.tourguide.user.dao;

import java.util.UUID;

import com.tourguide.user.domain.UserPreferences;

/**
 * User preference dao
 * 
 * @author David
 * @see UserPreferences
 *
 */
public interface UserPreferenceDao {

	/**
	 * Find by user id
	 * 
	 * @param userId
	 * @return user preference at this user id
	 * @see UserPreferences
	 */
	UserPreferences findByUserId(UUID userId);

	/**
	 * Find by user name
	 * 
	 * @param userName : user name
	 * @return user preference at this user name
	 * @see UserPreferences
	 */
	UserPreferences findByUserName(String userName);

	/**
	 * Save user preference
	 * @param UserPreferences : user preference save
	 * @return user preference save
	 */
	UserPreferences save(UserPreferences UserPreferences);

	/**
	 * Delete user preference of user id
	 * @param userId : user id
	 */
	void delete(UUID userId);

	/**
	 * Delete user preference of user name
	 * @param userName : user name
	 */
	void delete(String userName);

}
