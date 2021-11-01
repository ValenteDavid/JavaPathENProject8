package com.tourguide.user.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourguide.user.UserApplication;
import com.tourguide.user.domain.User;
import com.tourguide.user.domain.UserPreferences;
import com.tourguide.user.helper.InternalTestHelper;
import com.tourguide.user.service.UserPreferenceService;
import com.tourguide.user.service.UserService;

@SpringBootTest(classes = UserApplication.class)
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserPreferenceService userPreferenceService;
	
	@Test
	public void addUser() {
		String userName = "25internalUser25";
		User user = new User(UUID.nameUUIDFromBytes(userName.getBytes()), userName, "000", "jon@tourGuide.com");
		String userName2 = "32internalUser32";
		User user2 = new User(UUID.nameUUIDFromBytes(userName2.getBytes()), userName2, "000", "jon@tourGuide.com");
		
		InternalTestHelper.setInternalUserNumber(0);
		
		userService.addUser(user);
		userService.addUser(user2);
		
		User retrivedUser = userService.getUser(user.getUserName());
		User retrivedUser2 = userService.getUser(user2.getUserName());

		assertEquals(user, retrivedUser);
		assertEquals(user2, retrivedUser2);
		
		assertEquals(user.getUserId(), retrivedUser.getUserId());
		assertEquals(user.getUserName(), retrivedUser.getUserName());
		assertEquals(user.getPhoneNumber(), retrivedUser.getPhoneNumber());
		assertEquals(user.getEmailAddress(), retrivedUser.getEmailAddress());
	}
	
	@Test
	public void getAllUsers() {
		String userName = "internalUser25";
		User user = new User(UUID.nameUUIDFromBytes(userName.getBytes()), userName, "000", "jon@tourGuide.com");
		String userName2 = "internalUser32";
		User user2 = new User(UUID.nameUUIDFromBytes(userName2.getBytes()), userName2, "000", "jon@tourGuide.com");
		
		InternalTestHelper.setInternalUserNumber(0);
		
		userService.addUser(user);
		userService.addUser(user2);
		
		List<User> allUsers = userService.getAllUsers();

		assertTrue(allUsers.contains(user));
		assertTrue(allUsers.contains(user2));
	}
	
	@Test
	public void getUserPreference() {
		String userName = "internalUser25";
		UserPreferences userPreference = new UserPreferences(UUID.nameUUIDFromBytes(userName.getBytes()),userName,5,5,5,0);
		
		InternalTestHelper.setInternalUserNumber(0);
		
		userPreferenceService.save(userPreference);
		UserPreferences retrivedUserPreference = userPreferenceService.get(userName);
		

		assertEquals(userPreference, retrivedUserPreference);
		
		assertEquals(userPreference.getUserName(), retrivedUserPreference.getUserName());
		assertEquals(userPreference.getTripDuration(), retrivedUserPreference.getTripDuration());
		assertEquals(userPreference.getTicketQuantity(), retrivedUserPreference.getTicketQuantity());
		assertEquals(userPreference.getNumberOfAdults(), retrivedUserPreference.getNumberOfAdults());
		assertEquals(userPreference.getNumberOfChildren(), retrivedUserPreference.getNumberOfChildren());
	}

}
