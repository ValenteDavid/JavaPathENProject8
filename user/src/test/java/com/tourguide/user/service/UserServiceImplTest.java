package com.tourguide.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tourguide.user.UserApplication;
import com.tourguide.user.dao.UserDao;
import com.tourguide.user.domain.User;
import com.tourguide.user.helper.InternalTestHelper;

@SpringBootTest(classes = UserApplication.class)
class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserDao userDao;

	@Test
	public void addUser() {
		InternalTestHelper.setInternalUserNumber(0);

		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		when(userDao.save(user)).thenReturn(user);
		userService.addUser(user);

		when(userDao.findByUserName(user.getUserName())).thenReturn(user);
		User retrivedUser = userService.getUser(user.getUserName());

//		tourGuideService.tracker.stopTracking();

		assertEquals(user, retrivedUser);
	}

}
