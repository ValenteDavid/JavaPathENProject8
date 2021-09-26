package com.tourguide.user.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.user.controller.dto.UserPreferenceDto;
import com.tourguide.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName) {
		return userService.getUser(userName).getUserId();
	}
	
	@RequestMapping("/getUserPreference")
	public UserPreferenceDto getUserPreference(@RequestParam String userName) {
		return UserPreferenceDto.convertToDto(userService.getUserPreference(userName));
	}


}
