package com.tourguide.user.service;

import java.util.UUID;

import com.tourguide.user.domain.UserPreferences;

/**
 * User preference service
 * 
 * @author David
 *
 */
public interface UserPreferenceService {

	/**
	 * Get user preference
	 * 
	 * @param userId
	 * @return user preference
	 * @see UserPreferences
	 */
	UserPreferences get(UUID userId);

	/**
	 * Get user preference
	 * @param userName
	 * @return user preference
	 * @see UserPreferences
	 */
	UserPreferences get(String userName);

	/**
	 * Save user preference
	 * @param userPreferences : user preference
	 * @return user preference
	 * @see UserPreferences
	 */
	UserPreferences save(UserPreferences userPreferences);

	/**
	 * Update user preference
	 * @param userPreferences : user preference
	 * @return user preference
	 * @see UserPreferences
	 */
	UserPreferences update(UserPreferences userPreferences);

	/**
	 * Delete user preference
	 * @param userId : user id
	 * @see UserPreferences
	 */
	void delete(UUID userId);

	/**
	 * Delete user preference
	 * @param userName : user name
	 * @see UserPreferences
	 */
	void delete(String userName);

}
