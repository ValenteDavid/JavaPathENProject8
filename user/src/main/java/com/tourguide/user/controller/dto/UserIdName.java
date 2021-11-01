package com.tourguide.user.controller.dto;

import java.util.UUID;

public class UserIdName {
	
	private UUID userId;
	private String userName;
	
	public UserIdName() {
	}

	public UserIdName(UUID userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
