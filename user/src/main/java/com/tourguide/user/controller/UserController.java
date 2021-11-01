package com.tourguide.user.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.user.controller.dto.UserIdName;
import com.tourguide.user.domain.User;
import com.tourguide.user.service.UserService;

/**
 * User controller
 * @author David
 *
 */
@RestController
public class UserController {

	/**
	 * @see UserService
	 */
	@Autowired
	private UserService userService;

	/**
	 * Endpoint /getUserId
	 * @param userName : user name
	 * @return user id
	 * @see User
	 */
	@RequestMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName) {
		return userService.getUser(userName).getUserId();
	}

	/**
	 * Endpoint /getUserIdAndUserNameAll
	 * @return list user id and user name
	 * @see UserIdName
	 */
	@RequestMapping("/getUserIdAndUserNameAll")
	public List<UserIdName> getUserIdAndUserNameAll() {
		return userService.getUserIdentification();
	}

}
