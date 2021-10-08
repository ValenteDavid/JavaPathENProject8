package com.tourguide.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tourguide.user.domain.User;
import com.tourguide.user.helper.InternalTestHelper;
import com.tourguide.user.service.UserService;

@SpringBootTest(classes = UserApplication.class)
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
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

//		tourGuideService.tracker.stopTracking();
		
		assertEquals(user, retrivedUser);
		assertEquals(user2, retrivedUser2);
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

//		tourGuideService.tracker.stopTracking();
		
		assertTrue(allUsers.contains(user));
		assertTrue(allUsers.contains(user2));
	}

}
