package com.tourguide.user.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.user.controller.dto.UserPreferenceCrudDto;
import com.tourguide.user.controller.dto.UserPreferenceDto;
import com.tourguide.user.domain.UserPreferences;
import com.tourguide.user.service.UserPreferenceService;

/**
 * User preference controller
 * @author David
 */
@RestController
public class UserPreferenceController {
	
	/**
	 * path
	 */
	private static final String path = "/userPreference";
	
	/**
	 * @see UserPreferenceService
	 */
	@Autowired
	private UserPreferenceService userPreferenceService;
	
	/**
	 * Endpoint /getUserPreference
	 * @param userName
	 * @return user preference
	 * @see UserPreferenceDto
	 */
	@RequestMapping("/getUserPreference")
	public UserPreferenceDto getUserPreference(@RequestParam String userName) {
		return UserPreferenceDto.convertToDto(get(userName));
	}
	
	/**
	 * Endpoint /userPreference//{userId}
	 * @param userId : user id
	 * @return user preference of  user id
	 * @see UserPreferences
	 */
	@GetMapping(path + "/{userId}")
	public UserPreferences get(@PathVariable UUID userId) {
		return userPreferenceService.get(userId);
	}
	
	/**
	 * Endpoint /userPreference/{userName}
	 * @param userName : user name
	 * @return user preference of  user name
	 * @see UserPreferences
	 */
	@GetMapping(path + "/{userName}")
	public UserPreferences get(@PathVariable String userName) {
		return userPreferenceService.get(userName);
	}
	
	/**
	 * Save user preference
	 * @param userPreferenceCrudDto : user preference to save
	 * @return user preference save
	 * @see UserPreferences
	 */
	@PostMapping(path)
	public UserPreferences save(@Valid @RequestBody UserPreferenceCrudDto userPreferenceCrudDto) {
		UserPreferences userPreferences = UserPreferenceCrudDto.convertToDomain(userPreferenceCrudDto,new UserPreferences(userPreferenceCrudDto.getUserId(), userPreferenceCrudDto.getUserName()));
		return userPreferenceService.save(userPreferences);
	}
	
	/**
	 * Update user preference
	 * @param userPreferenceCrudDto: user preference to update
	 * @return user preference Update
	 * @see UserPreferences
	 */
	@PutMapping(path)
	public UserPreferences update(@Valid @RequestBody UserPreferenceCrudDto userPreferenceCrudDto) {
		UserPreferences userPreferences = UserPreferenceCrudDto.convertToDomain(userPreferenceCrudDto,get(userPreferenceCrudDto.getUserId()));
		return userPreferenceService.update(userPreferences);
	}
	
	/**
	 * Delete user preference of user id
	 * @param userId : user id
	 * @see UserPreferences
	 */
	@DeleteMapping(path + "/{userId}")
	public void delete(@PathVariable UUID userId) {
		userPreferenceService.delete(userId);
	}
	
	/**
	 * Delete user preference of user name
	 * @param userName : user name
	 * @see UserPreferences
	 */
	@DeleteMapping(path + "{userName}")
	public void delete(@PathVariable String userName) {
		userPreferenceService.delete(userName);
	}

}
